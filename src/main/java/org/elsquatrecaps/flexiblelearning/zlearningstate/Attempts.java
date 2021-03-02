/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.elsquatrecaps.flexiblelearning.zlearningstate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.elsquatrecaps.flexiblelearning.zsyncdata.SyncList;

/**
 *
 * @author professor
 */
public class Attempts {

    private String activityId;
    private String studentId;

    private SyncList<Attempt> attempts= new SyncList<>(); //attempts of activity
    
    
    /**
     * Get the value of activity
     *
     * @return the value of activity
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * Set the value of activity
     *
     * @param activity new value of activity
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * get attempts
     * @return value of attempts
     */
    public SyncList<Attempt> getAttempts() {
        return attempts;
    }

    /**
     * set attempts. <b>Don't use.</b> Only for compatibility purposes.
     * @param attempts 
     */


    public void setAttempts(SyncList<Attempt> attempts) {
        this.attempts = attempts;
    }

    

    /**
     * Get the value of student
     *
     * @return the value of student
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Set the value of student
     *
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
   
///////////// end of accessors    
    
    /**
     * get list of finished attempts
     * @return list of finished attempts
     */
    public List<Attempt> finishedAttemptsList(){
       List<Attempt> result= new ArrayList<>();
       for(Attempt a:attempts){
           if(a.getEndLocalDateTime()!=null)  result.add(a);
       }
       return result;
    }
    
    /**
     * get list of unfinished attempts
     * @return list of unfinished attempts
     */
    public List<Attempt> unFinishedAttemptsList(){
       List<Attempt> result= new ArrayList<>();
       for(Attempt a:attempts){
           if(a.getEndLocalDateTime()==null)  result.add(a);
       }
       return result;
    }
    
    /**
     * Adds an attempt. 
     * @param attempt attempt to be added
     */
    
    protected void addAttempt(Attempt attempt){
        this.attempts.add(attempt);
    }
    
}