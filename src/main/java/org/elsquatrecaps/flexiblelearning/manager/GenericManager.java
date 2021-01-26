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
import org.elsquatrecaps.flexiblelearning.manager.starter.StarterManager;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 *
 * @author josep
 */
public abstract class GenericManager<LS, PMLS extends PagingAndSortingRepository<LS, String>&QueryByExampleExecutor<LS>, 
        LP, PMLP extends PagingAndSortingRepository<LP, String>&QueryByExampleExecutor<LP>, A, PMA extends PagingAndSortingRepository<A, String>&QueryByExampleExecutor<A>>{
    PMLS learningStateRepository;
    PMLP learningProposalRepository;
    PMA activityRepository;
    
    public void init(PMLS learningStateRepository, 
            PMLP learningProposalRepository, 
            PMA  activityRepository){
        this.activityRepository = activityRepository;
        this.learningProposalRepository = learningProposalRepository;
        this.learningStateRepository = learningStateRepository;
    }

    protected A getActivityConfiguration(String id){
        A ret;
        Optional<A> result=null;
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
     
    protected LP getLearningProposalConfiguration(String id){
        LP ret;
        Optional<LP> result=null;
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
    
    protected LS getLearningState(LS examplels){
        LS ret = null;
        Optional<LS> result = null;
        result = learningStateRepository.findOne(Example.of(examplels));
  
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
}
