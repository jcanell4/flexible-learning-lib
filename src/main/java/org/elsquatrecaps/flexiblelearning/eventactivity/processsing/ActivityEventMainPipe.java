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
package org.elsquatrecaps.flexiblelearning.eventactivity.processsing;

import java.util.LinkedList;
import java.util.List;
import org.elsquatrecaps.flexiblelearning.eventactivity.request.EventData;
import org.elsquatrecaps.flexiblelearning.eventactivity.responses.EventResponseData;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 *
 * @author josep
 */
public class ActivityEventMainPipe implements RootEventProcessor{
    private ActivityEventDataModelConverter eventDataHandler;
    private List<EventProcessor> eventProcessorList;
    private ActivityEventResponseHandler eventResponseGeerator;

    public ActivityEventMainPipe() {
        eventProcessorList = new LinkedList<>();
    }

    public ActivityEventMainPipe(ActivityEventDataModelConverter eventDataHandler, ActivityEventResponseHandler eventResponseGeerator) {
        this.eventDataHandler = eventDataHandler;
        this.eventProcessorList = new LinkedList<>();
        this.eventResponseGeerator = eventResponseGeerator;
    }

 
    @PersistenceConstructor
    public ActivityEventMainPipe(ActivityEventDataModelConverter eventDataHandler, 
            List<EventProcessor> eventProcessorList, 
            ActivityEventResponseHandler eventResponseGeerator) {
        this.eventDataHandler = eventDataHandler;
        this.eventProcessorList = eventProcessorList;
        this.eventResponseGeerator = eventResponseGeerator;
    }

    
    @Override
    public EventResponseData process(EventData eventData) {
        EventResponseData ret;
        EventDataToProcess dataToProcess;
        dataToProcess = eventDataHandler.getIntermediateDataModel(eventData);
        for(EventProcessor eventProcessor : eventProcessorList){
            dataToProcess = eventProcessor.process(dataToProcess);
        }
        ret = eventResponseGeerator.getResponseData(dataToProcess);
        return ret;
    }  

    public ActivityEventDataModelConverter getEventDataHandler() {
        return eventDataHandler;
    }

    public List<EventProcessor> getEventProcessorList() {
        return eventProcessorList;
    }

    public ActivityEventResponseHandler getEventResponseGeerator() {
        return eventResponseGeerator;
    }

    public void setEventDataHandler(ActivityEventDataModelConverter eventDataHandler) {
        this.eventDataHandler = eventDataHandler;
    }

    public void setEventResponseGeerator(ActivityEventResponseHandler eventResponseGeerator) {
        this.eventResponseGeerator = eventResponseGeerator;
    }
    
//    public void setPreProcessingPipe(ActionEventGenericPipe preProcessingPipe) {
//        this.preProcessingPipe = preProcessingPipe;
//    }
//
//    public void setUserResponseEvaluationPipe(ActivityEventUserResponseEvaluationPipe userResponseEvaluationPipe) {
//        this.userResponseEvaluationPipe = userResponseEvaluationPipe;
//    }
//
//    public void setMethodologyPipe(ActivityEventMethodologyPipe methodologyPipe) {
//        this.methodologyPipe = methodologyPipe;
//    }
//
//    public void setPostProcessingPipe(ActionEventGenericPipe postProcessingPipe) {
//        this.postProcessingPipe = postProcessingPipe;
//    }
//    
//    public void removePreProcessingPipe() {
//        this.preProcessingPipe = null;
//    }
//
//    public void removeUserResponseEvaluationPipe() {
//        this.userResponseEvaluationPipe = null;
//    }
//
//    public void removeMethodologyPipe() {
//        this.methodologyPipe = null;
//    }
//
//    public void removePostProcessingPipe() {
//        this.postProcessingPipe = null;
//    }
    
}
