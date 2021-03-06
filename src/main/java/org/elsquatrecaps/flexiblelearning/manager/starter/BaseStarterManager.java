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

import org.elsquatrecaps.flexiblelearning.manager.GenericManager;
import org.elsquatrecaps.flexiblelearning.learningproposal.ActivityConfiguration;
import org.elsquatrecaps.flexiblelearning.learningproposal.LearningProposalConfiguration;
import org.elsquatrecaps.flexiblelearning.learningstate.LearningState;
import org.elsquatrecaps.flexiblelearning.viewcomposer.ResponseViewComposer;
import org.elsquatrecaps.flexiblelearning.viewcomposer.ResponseViewComposerfactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.ResponseViewComponentSingleFragment;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.ResponseViewComponent;

/**
 *
 * @author josep
 * @param <LSR>
 * @param <LPR>
 * @param <AR>
 */
public class BaseStarterManager<LSR extends PagingAndSortingRepository<LearningState, String>&QueryByExampleExecutor<LearningState>, 
        LPR extends PagingAndSortingRepository<LearningProposalConfiguration, String>&QueryByExampleExecutor<LearningProposalConfiguration>, 
        AR extends PagingAndSortingRepository<ActivityConfiguration, String>&QueryByExampleExecutor<ActivityConfiguration>>
        extends GenericManager<LSR, LPR, AR>         
        implements StarterManager<LSR, LPR, AR>{

    public ModelAndView start(String studentId, String learningProposalId){
        ModelAndView modelAndView = null;
        
        LearningState ls = getLearningState(new LearningState(studentId, learningProposalId));
        LearningProposalConfiguration lp = getLearningProposalConfiguration(learningProposalId);
        ActivityConfiguration ac = getActivityConfiguration(ls.getCurrentActivityId());
        modelAndView = getModelFromLearningProposalConfiguration(lp, ac, ls);
        return modelAndView;
    }
    
    protected ModelAndView getModelFromLearningProposalConfiguration(LearningProposalConfiguration learningProposalConfiguration, 
            ActivityConfiguration activityConfiguration, 
            LearningState learningState) {
        ModelAndView model;
        ResponseViewComposer responseViewComposer = ResponseViewComposerfactory.getResponseViewComposerInstance(learningProposalConfiguration);
        responseViewComposer.addAdditionalData("learningState", learningState);
        model = responseViewComposer.getResponseView();        
        responseViewComposer.addComponent("activityComponent", activityConfiguration, model);
        
        return model;
    }
}
