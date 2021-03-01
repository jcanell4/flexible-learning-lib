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
package org.elsquatrecaps.flexiblelearning.manager;

import java.util.Optional;
import java.util.function.Supplier;
import org.elsquatrecaps.flexiblelearning.learningproposal.ActivityConfiguration;
import org.elsquatrecaps.flexiblelearning.learningproposal.LearningProposalConfiguration;
import org.elsquatrecaps.flexiblelearning.learningstate.LearningState;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 *
 * @author josep
 */
public abstract class GenericManager<LSR extends PagingAndSortingRepository<LearningState, String>&QueryByExampleExecutor<LearningState>, 
        LPR extends PagingAndSortingRepository<LearningProposalConfiguration, String>&QueryByExampleExecutor<LearningProposalConfiguration>, 
        AR extends PagingAndSortingRepository<ActivityConfiguration, String>&QueryByExampleExecutor<ActivityConfiguration>>{
    LSR learningStateRepository;
    LPR learningProposalRepository;
    AR activityRepository;
    
    public void init(LSR learningStateRepository, 
            LPR learningProposalRepository, 
            AR  activityRepository){
        this.activityRepository = activityRepository;
        this.learningProposalRepository = learningProposalRepository;
        this.learningStateRepository = learningStateRepository;
    }

    protected ActivityConfiguration getActivityConfiguration(String id){
        ActivityConfiguration ret;
        Optional<ActivityConfiguration> result=null;
        result = activityRepository.findById(id);
         try {
            ret = result.orElseThrow(new Supplier<Exception>() {
                @Override
                public Exception get() {
                    Exception ex = new RuntimeException("ACTIVITY_NOT_FOUND");
                    return ex;
                }
            });
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
         return ret;
    }
     
    protected LearningProposalConfiguration getLearningProposalConfiguration(String id){
        LearningProposalConfiguration ret;
        Optional<LearningProposalConfiguration> result=null;
        result = learningProposalRepository.findById(id);
         try {
            ret = result.orElseThrow(new Supplier<Exception>() {
                @Override
                public Exception get() {
                    Exception ex = new RuntimeException("LEARNING_PROPOSAL_NOT_FOUND");
                    return ex;
                }
            });
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
         return ret;
    }
    
    protected LearningState getLearningState(LearningState examplels){
        LearningState ret = null;
        Optional<LearningState> result = null;
        result = learningStateRepository.findOne(Example.of(examplels));
  
        try {
            ret = result.orElseThrow(new Supplier<Exception>() {
                @Override
                public Exception get() {
                    Exception ex = new RuntimeException("LEARNING_STATE_NOT_FOUND");
                    return ex;
                }
            });
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
       
        
        return ret;
    }
}
