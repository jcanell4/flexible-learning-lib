/*
 * Copyright 2020 Grup de millora MetFlex.
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
package org.elsquatrecaps.flexiblelearning.learningstate;

/**
 *
 * @author josep
 */
public class ResponseViewId {
    private final String learningProposalId;
    private final String activityId;

    public ResponseViewId(String learningProposalId, String activityId) {
        this.learningProposalId = learningProposalId;
        this.activityId = activityId;
    }
    
    /**
     * @return the learningProposalId
     */
    public String getLearningProposalId() {
        return learningProposalId;
    }

    /**
     * @return the activityId
     */
    public String getActivityId() {
        return activityId;
    }   
}
