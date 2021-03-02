/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.elsquatrecaps.flexiblelearning.zlearningstate;

import org.elsquatrecaps.flexiblelearning.zsyncdata.SyncMap;
import java.time.LocalDateTime;
import java.util.Map;
import org.elsquatrecaps.flexiblelearning.learningproposal.ActivityConfiguration;


public class AttemptStatus <T>{
    
    private LocalDateTime time;
    private SyncMap<String,Object> studentInputs = new SyncMap<>();
    private ActivityConfiguration activityConfiguration = new ActivityConfiguration();
    private SyncMap<String,Object> additionalData = new SyncMap<>();
    

    
    /**
     * Get the value of an Item
     *
     * @param itemName name of the item wich state will be returned
     * @return the state of the item
     */
    public Object getState(String itemName) {
        return studentInputs.get(itemName);
    }

    /**
     * Set the state corresponding to the item identified by itemName
     *
     * @param itemName name and identification (inside the scoreScheme) of an item
     * @param state new state of the item identified by itemName
     */
    public void setState(String itemName,Object state) {
        studentInputs.put(itemName, state);
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    
    /**
     * Get the value of additionalData
     *
     * @return the value of additionalData
     */
    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }

    /**
     * Set the value of additionalData
     *
     * @param additionalData new value of additionalData
     */
    public void setAdditionalData(SyncMap<String, Object> additionalData) {
        this.additionalData = additionalData;
    }
    
    

    /**
     * Get the value of activityConfiguration
     *
     * @return the value of activityConfiguration
     */
    public ActivityConfiguration getActivityConfiguration() {
        return activityConfiguration;
    }

    /**
     * Set the value of activityConfiguration
     *
     * @param activityConfiguration new value of activityConfiguration
     */
    public void setActivityConfiguration(ActivityConfiguration activityConfiguration) {
        this.activityConfiguration = activityConfiguration;
    }

    public void studentInputsToStatus(ModelMap mp){
        studentInputs.clear();
        studentInputs.putAll(mp);
    }
    
    public void studentInputsToModel(ModelAndView mv){
        mv.getModel().putAll(studentInputs);
    }
    
}
