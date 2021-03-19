/*
 * Copyright 2021 Grup de millora MetFlex.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elsquatrecaps.flexiblelearning.zlearningstate;

import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.elsquatrecaps.flexiblelearning.learningproposal.ActivityConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author professor
 */
class AttemptStatus {

    private LocalDateTime storedLocalDateTime;
    private Map<String,Object> studentInputs = new HashMap<>();
    private ActivityConfiguration activityConfiguration = null;
    private Map<Integer,Object> attemptStatus = new HashMap<>();



    public Map<String, Object> getStudentInputs() {
        return studentInputs;
    }

    public void setStudentInputs(Map<String, Object> studentInputs) {
        this.studentInputs = studentInputs;
    }

    public ActivityConfiguration getActivityConfiguration() {
        return activityConfiguration;
    }

    public void setActivityConfiguration(ActivityConfiguration activityConfiguration) {
        this.activityConfiguration = activityConfiguration;
    }

    public Map<Integer, Object> getAttemptStatus() {
        return attemptStatus;
    }

    public void setAttemptStatus(Map<Integer, Object> attemptStatus) {
        this.attemptStatus = attemptStatus;
    }

            
    /**
     * Get the value of storedLocalDateTime
     *
     * @return the value of storedLocalDateTime
     */
    public LocalDateTime getStoredLocalDateTime() {
        return storedLocalDateTime;
    }

    /**
     * Set the value of storedLocalDateTime
     *
     * @param storedLocalDateTime new value of storedLocalDateTime
     */    
    
    public void setStoredLocalDateTime(LocalDateTime storedLocalDateTime) {
        this.storedLocalDateTime = storedLocalDateTime;
    }  
    
    /**
     * Get the value of an Item entered by an user
     *
     * @param itemName name of the item wich value will be returned
     * @return the state of the item
     */
    public Object getStudentInput(String itemName) {
        return studentInputs.get(itemName);
    }

    /**
     * Set the value of an item entered by an user
     *
     * @param itemName name and identification (inside the scoreScheme) of an item
     * @param state new state of the item identified by itemName
     */
    public void setStudentInput(String itemName,Object state) {
        studentInputs.put(itemName, state);
    }    
    
///////////////////////////////////////////////////// end of setters and getters    
    
    private void loadStudentInputs(ModelMap mp){
        studentInputs.clear();
        studentInputs.putAll(mp);
    }
    /**
     * copy last student inputs to current ModelAndView
     * @param mv current ModelAndView
     */
    public void exportStudentInputs(ModelAndView mv){
        mv.getModel().putAll(studentInputs);
    }    

    /**
     * calculates AttemptStatus from studentInputs and previous AttemptStatus
     */

    public void updateAttemptStatus(StatusScheme ssch, ModelMap mp) {
        final String procedure=ssch.getProcedure();
        
        if(procedure!=null){
            final String statusName="$context",inputsName="$form";
            
            loadStudentInputs(mp);
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");



            Map<String,Object> statusValues=new HashMap<>();

            for(int i:attemptStatus.keySet()){
                SchemeItem aux=ssch.get(i);
                if(aux!=null){
                    statusValues.put(aux.getPathName(), attemptStatus.get(i));
                }
            }
            Gson gson=new Gson();
            String statusValuesMap=gson.toJson(statusValues);
            String studentInputsMap=gson.toJson(studentInputs);


            try {
                engine.eval(statusName+"="+statusValuesMap+";");
                engine.eval(inputsName+"="+studentInputsMap+";");
                engine.eval(procedure);
                statusValuesMap=engine.eval("JSON.stringify("+statusName+");").toString();
                statusValues=gson.fromJson(statusValuesMap, statusValues.getClass());
                
                for(String k:statusValues.keySet()){
                    SchemeItem aux=ssch.get(k);
                    if(aux!=null){
                        if(aux.getClasz()==Integer.class)attemptStatus.put(aux.getNumber(),(int)Math.round((Double)statusValues.get(k)));
                        else attemptStatus.put(aux.getNumber(),statusValues.get(k));
                    }
                }
                
                
            }catch (ScriptException ex) {
                    Logger.getLogger(AttemptStatus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
