/*
 * Copyright 2020 Grup de millora MetFlex.
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
package org.elsquatrecaps.flexiblelearning.eventcomposer;

import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.ActionEventGenericPipe;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.EventDataToProcessConverter;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.EventProcessor;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.factory.EventProcessorComponentFactory;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.EventComponentConfiguration;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.GroupingOfEventProcessorsConfiguration;

/**
 * Standard ResponseViewComposer inatantiation. 
 * @author josep
 */
public class BaseEventProcessorPipeComposer extends BaseEventProcessorComposer{
    public BaseEventProcessorPipeComposer() {
    }

    public BaseEventProcessorPipeComposer(EventComponentConfiguration config) {
        super(config);
        if(!(config instanceof GroupingOfEventProcessorsConfiguration)){
            throw new IllegalArgumentException(String.format("LA_CONFIGURACIO_HA_DE_SER_DE_TIPUS %s", "GroupingOfEventProcessorsConfiguration")); //internacionalitzar
        }
    }
    
    public BaseEventProcessorPipeComposer(GroupingOfEventProcessorsConfiguration config) {
        super(config);
    }

    public EventProcessor getEventProcessor(EventComponentConfiguration configuration) {
        if(configuration instanceof GroupingOfEventProcessorsConfiguration){
            return this.getEventProcessor((GroupingOfEventProcessorsConfiguration) configuration);
        }else{
            throw new IllegalArgumentException(String.format("LA_CONFIGURACIO_HA_DE_SER_DE_TIPUS %s", "GroupingOfEventProcessorsConfiguration")); //internacionalitzar
        }
    }
    
    public EventProcessor getEventProcessor(GroupingOfEventProcessorsConfiguration configuration) {
        ActionEventGenericPipe ret = (ActionEventGenericPipe) super.getEventProcessor(configuration);
        if(ret!=null){
            EventDataToProcessConverter converter = this.getEventDataConverter(configuration.getEventDataConverterConfiguration());
            ret.setConverter(converter);
            for (EventComponentConfiguration eventComponentConfiguration : configuration.getEventComponentConfigurationList()) {
                    EventProcessorComposer composer = EventProcessorComposerFactory.getEventComposerInstance(eventComponentConfiguration);
                    EventProcessor eventProcessor = composer.getEventProcessor(eventComponentConfiguration);
                    ret.getEventProcessorList().add(eventProcessor);
            }
        }

        return ret;
    }
    
    private EventDataToProcessConverter getEventDataConverter(EventComponentConfiguration configuration){
        EventDataToProcessConverter ret;
        registerSuppliersFromConfiguration(configuration);
        ret = EventProcessorComponentFactory.instanceEventDataToProcessConverter(configuration.getFullName());
        return ret;
    }


}
