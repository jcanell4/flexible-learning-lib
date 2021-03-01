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
package org.elsquatrecaps.flexiblelearning.eventcomposer.components;

import java.util.LinkedList;
import java.util.List;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.RootEventProcessor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author josep
 */
@Document
@ActivityEventProcessorComposerConfigurator
public class BaseActivityEventProcessorConfiguration extends BaseEventComponentConfiguration implements ActivityEventProcessorConfiguration{
    @Id
    private ActivityEventProcessorId id;
    private EventComponentConfiguration eventDataModeConverterConfiguration;
    private EventComponentConfiguration eventResponseHandlerConfiguration;
    private List<EventComponentConfiguration> eventComponentConfigurationList;
    private RootEventProcessor cache;

    public BaseActivityEventProcessorConfiguration(
            ActivityEventProcessorId id,
            EventComponentConfiguration eventDataModeConverterConfiguration, 
            EventComponentConfiguration eventResponseHandlerConfiguration) {
        super("default", "ActivityEventMainPipe");
        this._init(id, eventDataModeConverterConfiguration, 
                new LinkedList<EventComponentConfiguration>(),
                eventResponseHandlerConfiguration);
    }

    public BaseActivityEventProcessorConfiguration(
            ActivityEventProcessorId id,
            String collection, String name,
            EventComponentConfiguration eventDataModeConverterConfiguration, 
            EventComponentConfiguration eventResponseHandlerConfiguration) {
        super(collection, name);
        this._init(id, eventDataModeConverterConfiguration, 
                new LinkedList<EventComponentConfiguration>(),
                eventResponseHandlerConfiguration);
    }

    @PersistenceConstructor
    public BaseActivityEventProcessorConfiguration(ActivityEventProcessorId id,
            String collection, String name, 
            EventComponentConfiguration eventDataModeConverterConfiguration, 
            List<EventComponentConfiguration> eventComponentConfigurationList, 
            EventComponentConfiguration eventResponseHandlerConfiguration) {
        super(collection, name);
        this._init(id, eventDataModeConverterConfiguration, eventComponentConfigurationList, eventResponseHandlerConfiguration);
    }
    
    @PersistenceConstructor
    public BaseActivityEventProcessorConfiguration(ActivityEventProcessorId id,
            String collection, String name, 
            EventComponentConfiguration eventDataModeConverterConfiguration, 
            List<EventComponentConfiguration> eventComponentConfigurationList, 
            EventComponentConfiguration eventResponseHandlerConfiguration,
            RootEventProcessor cache) {
        super(collection, name);
        this._init(id, eventDataModeConverterConfiguration, eventComponentConfigurationList, eventResponseHandlerConfiguration);
        this.cache = cache;
    }
    
    private void _init(ActivityEventProcessorId id, 
            EventComponentConfiguration eventDataModeConverterConfiguration, 
            List<EventComponentConfiguration> eventComponentConfigurationList, 
            EventComponentConfiguration eventResponseHandlerConfiguration) {
        this.id = id;
        this.eventDataModeConverterConfiguration = eventDataModeConverterConfiguration;
        this.eventComponentConfigurationList = eventComponentConfigurationList;
        this.eventResponseHandlerConfiguration = eventResponseHandlerConfiguration;
    }
    
    

    public EventComponentConfiguration getEventDataModeConverterConfiguration() {
        return eventDataModeConverterConfiguration;
    }

    public EventComponentConfiguration getEventResponseHandlerConfiguration() {
        return eventResponseHandlerConfiguration;
    }

    public void setEventDataModeConverterConfiguration(EventComponentConfiguration eventDataModeConverterConfiguration) {
        this.eventDataModeConverterConfiguration = eventDataModeConverterConfiguration;
    }

    public void setEventResponseHandlerConfiguration(EventComponentConfiguration eventResponseHandlerConfiguration) {
        this.eventResponseHandlerConfiguration = eventResponseHandlerConfiguration;
    }

    @Override
    public RootEventProcessor getCache() {
        return cache;
    }

    public void setCache(RootEventProcessor cache) {
        this.cache = cache;
    }

    public ActivityEventProcessorId getId() {
        return id;
    }

    public List<EventComponentConfiguration> getEventComponentConfigurationList() {
        return eventComponentConfigurationList;
    }

}
