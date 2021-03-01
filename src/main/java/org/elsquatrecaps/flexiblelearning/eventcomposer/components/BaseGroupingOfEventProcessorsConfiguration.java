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
import org.elsquatrecaps.flexiblelearning.eventcomposer.BaseEventProcessorPipeComposer;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 *
 * @author josep
 */
@EventProcessorComposerConfigurator(eventProcessorComposerClass = BaseEventProcessorPipeComposer.class)
public class BaseGroupingOfEventProcessorsConfiguration extends BaseEventComponentConfiguration implements GroupingOfEventProcessorsConfiguration{
    private EventComponentConfiguration eventDataConverterConfiguration;
    private List<EventComponentConfiguration> eventComponentConfigurationList;

    public BaseGroupingOfEventProcessorsConfiguration() {
        super("default", "ActionEventGenericPipe");
        eventComponentConfigurationList = new LinkedList<>();
    }

    public BaseGroupingOfEventProcessorsConfiguration(String collection, String name) {
        super(collection, name);
        eventComponentConfigurationList = new LinkedList<>();
    }
    
    @PersistenceConstructor
    public BaseGroupingOfEventProcessorsConfiguration(String collection, String name, List<EventComponentConfiguration> eventComponentConfigurationList) {
        super(collection, name);
        this.eventComponentConfigurationList = eventComponentConfigurationList;
    }
    
    public EventComponentConfiguration getEventDataConverterConfiguration() {
        return eventDataConverterConfiguration;
    }

    public void setEventDataConverterConfiguration(EventComponentConfiguration eventDataConverterConfiguration) {
        this.eventDataConverterConfiguration = eventDataConverterConfiguration;
    }

    @Override
    public List<EventComponentConfiguration> getEventComponentConfigurationList() {
        return eventComponentConfigurationList;
    }
}
