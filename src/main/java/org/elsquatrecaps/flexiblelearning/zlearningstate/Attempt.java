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

/**
 *
 * @author professor
 */
public class Attempt {

    private String idAttempt;
    private String idAttemptList;
    private LocalDateTime endLocalDateTime;
    private LocalDateTime startLocalDateTime;  //set when attempt is finished
    private AttemptStatus attemptStatus = new AttemptStatus();





    /**
     * Get the value of idAttempt
     *
     * @return the value of idAttempt
     */
    public String getIdAttempt() {
        return idAttempt;
    }

    /**
     * Set the value of idAttempt
     *
     * @param idAttempt new value of idAttempt
     */
    public void setIdAttempt(String idAttempt) {
        this.idAttempt = idAttempt;
    }

    /**
     * Get the value of idAttemptList
     *
     * @return the value of idAttemptList
     */
    public String getIdAttemptList() {
        return idAttemptList;
    }

    /**
     * Set the value of idAttemptList
     *
     * @param idAttemptList new value of idAttemptList
     */
    public void setIdAttemptList(String idAttemptList) {
        this.idAttemptList = idAttemptList;
    }

    /**
     * Get the value of endLocalDateTime
     *
     * @return the value of score
     */
    
    public LocalDateTime getEndLocalDateTime() {
        return endLocalDateTime;
    }

    /**
     * Set the value of endLocalDateTime
     *
     * @param endLocalDateTime new value of endLocalDateTime
     */
   
    public void setEndLocalDateTime(LocalDateTime endLocalDateTime) {
        this.endLocalDateTime = endLocalDateTime;
    }

    /**
     * Get the value of startLocalDateTime
     *
     * @return the value of score
     */


    public LocalDateTime getStartLocalDateTime() {
        return startLocalDateTime;
    }

    /**
     * Set the value of startLocalDateTime
     *
     * @param startLocalDateTime new value of startLocalDateTime
     */
    
    public void setStartLocalDateTime(LocalDateTime startLocalDateTime) {
        this.startLocalDateTime = startLocalDateTime;
    }


    
}
