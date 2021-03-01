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
package org.elsquatrecaps.flexiblelearning.manager.event;

import org.elsquatrecaps.flexiblelearning.eventactivity.request.EventDataMap;
import org.elsquatrecaps.flexiblelearning.learningproposal.ActivityConfiguration;
import org.elsquatrecaps.flexiblelearning.learningproposal.LearningProposalConfiguration;
import org.elsquatrecaps.flexiblelearning.learningstate.LearningState;
import org.elsquatrecaps.flexiblelearning.eventactivity.responses.EventResponseData;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.ActivityEventProcessorConfiguration;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.ActivityEventProcessorId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author josep
 */
public interface EventManager <ER extends PagingAndSortingRepository<ActivityEventProcessorConfiguration, ActivityEventProcessorId>&QueryByExampleExecutor<ActivityEventProcessorConfiguration>,
        LSR extends PagingAndSortingRepository<LearningState, String>&QueryByExampleExecutor<LearningState>, 
        LPR extends PagingAndSortingRepository<LearningProposalConfiguration, String>&QueryByExampleExecutor<LearningProposalConfiguration>, 
        AR extends PagingAndSortingRepository<ActivityConfiguration, String>&QueryByExampleExecutor<ActivityConfiguration>> {

    void init(ER eventRepository, LSR learningStateRepository, LPR learningProposalRepository, AR  activityRepository);
    ModelAndView processEventAndResponseHtml(EventDataMap eventData);
    EventResponseData processEventAndResponseJson(String lpId, String stId, String event, EventDataMap eventData);
}
