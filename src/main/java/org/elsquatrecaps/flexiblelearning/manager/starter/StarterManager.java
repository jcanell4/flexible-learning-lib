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
package org.elsquatrecaps.flexiblelearning.manager.starter;

import org.elsquatrecaps.flexiblelearning.learningproposal.ActivityConfiguration;
import org.elsquatrecaps.flexiblelearning.learningproposal.LearningProposalConfiguration;
import org.elsquatrecaps.flexiblelearning.learningstate.LearningState;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 *
 * @author josep
 */
public interface StarterManager<PMLS extends PagingAndSortingRepository<LearningState, String>&QueryByExampleExecutor<LearningState>, 
        PMLP extends PagingAndSortingRepository<LearningProposalConfiguration, String>&QueryByExampleExecutor<LearningProposalConfiguration>, 
        PMA extends PagingAndSortingRepository<ActivityConfiguration, String>&QueryByExampleExecutor<ActivityConfiguration>>{

    void init(PMLS learningStateRepository, PMLP learningProposalRepository, PMA  activityRepository);
    ModelAndView start(String studentId, String learningProposalId);
    
}
