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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.elsquatrecaps.flexiblelearning.learningproposal.ActivityConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author professor
 */
class AttemptStatus {

    private String idStatusScheme;
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
     * Get the value of idStatusScheme;
     *
     * @return the value of idStatusScheme;
     */
    public String getIdStatusScheme() {
        return idStatusScheme;
    }

    /**
     * Set the value of idStatusScheme;
     *
     * @param idStatusScheme; new value of idStatusScheme;
     */
    public void setIdStatusScheme(String idStatusScheme) {
        this.idStatusScheme= idStatusScheme;
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
    
    public void loadStudentInputs(ModelMap mp){
        studentInputs.clear();
        studentInputs.putAll(mp);
    }
    
    public void exportStudentInputs(ModelAndView mv){
        mv.getModel().putAll(studentInputs);
        calculateAttemptStatus();
    }    

    /**
     * calculates AttemptStatus from studentInputs and previous AttemptStatus
     */
    //TODO
    public void calculateAttemptStatus() {
        ArrayList<Integer> updatedElements = new ArrayList<>();
        // adaptar el codi
        // executar les inicialitzacions amb valors del modelmap
        // executar el codi adaptat
        // recuperar els valors (només els que han tingut substitucions)
    }
    
    private String adaptCode(String code, StatusScheme scheme, ArrayList<Integer> updatedElements){
        StringBuffer newCode= new StringBuffer(code);
        
        for(int i:attemptStatus.keySet()){
            SchemeItem item=scheme.get(i);
            if(item!=null){
                StringBuffer name=new StringBuffer(item.getPathName());
                substituteByArray(newCode,name,i);
            }
        }
        
        return newCode.toString();
        
    }

    private void substituteByArray(StringBuffer code, StringBuffer name, int i) {  // ha de substituir a code totes les aparicions de name per $$[i]
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
