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

import java.util.Optional;
import java.util.function.Supplier;
import org.elsquatrecaps.flexiblelearning.learningproposal.ActivityConfiguration;
import org.elsquatrecaps.flexiblelearning.learningproposal.LearningProposalConfiguration;
import org.elsquatrecaps.flexiblelearning.learningstate.LearningState;
import org.elsquatrecaps.flexiblelearning.manager.GenericManager;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.ModelAndViewEventResponse;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.RootEventProcessor;
import org.elsquatrecaps.flexiblelearning.eventactivity.request.EventDataMap;
import org.elsquatrecaps.flexiblelearning.eventactivity.responses.EventResponseData;
import org.elsquatrecaps.flexiblelearning.eventcomposer.ActivityEventProcessorComposer;
import org.elsquatrecaps.flexiblelearning.eventcomposer.EventProcessorComposerFactory;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.ActivityEventProcessorConfiguration;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.ActivityEventProcessorId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 *
 * @author josep
 * @param <LSR>
 * @param <LPR>
 * @param <AR>
 */
public class BaseEventManager<ER extends PagingAndSortingRepository<ActivityEventProcessorConfiguration, ActivityEventProcessorId>&QueryByExampleExecutor<ActivityEventProcessorConfiguration>,
        LSR extends PagingAndSortingRepository<LearningState, String>&QueryByExampleExecutor<LearningState>, 
        LPR extends PagingAndSortingRepository<LearningProposalConfiguration, String>&QueryByExampleExecutor<LearningProposalConfiguration>, 
        AR extends PagingAndSortingRepository<ActivityConfiguration, String>&QueryByExampleExecutor<ActivityConfiguration>>
        extends GenericManager<LSR, LPR, AR>
        implements EventManager<ER, LSR, LPR, AR>{
    
    ER eventRepository;
    
    public void init(ER  eventRopository, LSR learningStateRepository, LPR learningProposalRepository, AR  activityRepository){
        super.init(learningStateRepository, learningProposalRepository, activityRepository);
        this.eventRepository = eventRopository;
    }

    @Override
    public ModelAndViewEventResponse processEventAndResponseHtml(EventDataMap eventData) {
        ModelAndViewEventResponse ret = null;
        String studentId = eventData.get("studentId");
        String LearningProposalId = eventData.get("learningProposalId");
        LearningState ls = getLearningState(new LearningState(studentId, LearningProposalId));
        ActivityConfiguration ac = getActivityConfiguration(ls.getCurrentActivityId());

        return ret;
    }

    @Override
    public EventResponseData processEventAndResponseJson(String learningProposalId, String studentId, String eventType, EventDataMap eventData) {
        EventResponseData ret=null;
        LearningState ls = getLearningState(new LearningState(studentId, learningProposalId));
        ActivityEventProcessorConfiguration activityEventProcessorConfiguration = getEventProcessorConfiguration(learningProposalId, ls.getCurrentActivityId(), eventType);
        ActivityEventProcessorComposer composer =  EventProcessorComposerFactory.getActivityEventComposerInstance(activityEventProcessorConfiguration);
        RootEventProcessor processor = composer.getRootEventProcessor(activityEventProcessorConfiguration);
        activityEventProcessorConfiguration.setCache(processor);
//        this.saveActivityEventProcessor(activityEventProcessorConfiguration);
        
        ret = processor.process(eventData);
        
        return ret;
    }    
    
    protected ActivityEventProcessorConfiguration getEventProcessorConfiguration(String learningProposalId, String activityId, String eventType){
        ActivityEventProcessorId activityEventProcessorId;
        activityEventProcessorId = new ActivityEventProcessorId(learningProposalId, activityId, eventType);
        ActivityEventProcessorConfiguration ret = null;
        Optional<ActivityEventProcessorConfiguration> result = null;
        result = eventRepository.findById(activityEventProcessorId);
  
        try {
            ret = result.orElseThrow(new Supplier<Exception>() {
                @Override
                public Exception get() {
                    Exception ex = new RuntimeException("ACTIVITY_EVENT_NOT_FOUND");
                    return ex;
                }
            });
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }               
        return ret;
    }
    
    protected void saveActivityEventProcessor(ActivityEventProcessorConfiguration activityEventConfiguration){
        this.eventRepository.save(activityEventConfiguration);
    }
    


}
