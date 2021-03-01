/*0
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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author josep
 */
public class ActionEventGenericPipe implements ActionEventPipe{
    public static final long serialVersionUID = 1L;
    private List<EventProcessor> eventProcessorList;
    private EventDataToProcessConverter converter=null; 

    public ActionEventGenericPipe() {
        this(new VoidEventDataConverter());
    }

    public ActionEventGenericPipe(EventDataToProcessConverter eventDataConverter) {
        this.converter = new VoidEventDataConverter();
        this.eventProcessorList = new LinkedList<>();
    }

    public ActionEventGenericPipe(EventDataToProcessConverter eventDataAdapter, List<EventProcessor> eventProcessorList) { 
        this.converter = eventDataAdapter;
        this.eventProcessorList = eventProcessorList;
    }
    
    
    
    public EventDataToProcess process(EventDataToProcess dataToProcess) {
        EventDataToProcess intermediateDate = this.converter.getDataToPRocess(dataToProcess);
        for(EventProcessor action: this.eventProcessorList){
            intermediateDate = action.process(intermediateDate);
        }
        dataToProcess = this.converter.getImputDataFromResponse(intermediateDate);
        
        return dataToProcess;
    }    

    public EventDataToProcessConverter getConverter() {
        return converter;
    }

    public void setConverter(EventDataToProcessConverter converter) {
        this.converter = converter;
    }

    public List<EventProcessor> getEventProcessorList() {
        return eventProcessorList;
    }
    
}
