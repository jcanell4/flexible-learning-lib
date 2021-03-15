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

/**
 *
 * @author professor
 */
public class AttemptList {

    private String  id;

    private String idStudent;

    private String idActivity;
    
    private String idStatusScheme;
    
    private String currentAttemptId;

    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the value of idStudent
     *
     * @return the value of idStudent
     */
    public String getIdStudent() {
        return idStudent;
    }

    /**
     * Set the value of idStudent
     *
     * @param idStudent new value of idStudent
     */
    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }


    
    /**
     * Get the value of idActivity
     *
     * @return the value of idActivity
     */
    public String getIdActivity() {
        return idActivity;
    }

    /**
     * Set the value of idActivity
     *
     * @param idActivity new value of idActivity
     */
    public void setIdActivity(String idActivity) {
        this.idActivity = idActivity;
    }

    /**
     * Get the value of idStatusScheme
     *
     * @return the value of idStatusScheme
     */
    public String getIdStatusScheme() {
        return idStatusScheme;
    }

    /**
     * Set the value of idStatusScheme
     *
     * @param idStatusScheme new value of idStatusScheme
     */
    public void setIdStatusScheme(String idStatusScheme) {
        this.idStatusScheme = idStatusScheme;
    }

    /**
     * Get the value of currentAttemptId
     *
     * @return the value of currentAttemptId
     */
    public String getCurrentAttemptId() {
        return currentAttemptId;
    }

    /**
     * Set the value of currentAttemptId
     *
     * @param currentAttemptId new value of currentAttemptId
     */
    public void setCurrentAttemptId(String currentAttemptId) {
        this.currentAttemptId = currentAttemptId;
    }
    
    
}
