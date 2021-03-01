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

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.ActivityEventDataModelConverter;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.ActivityEventResponseHandler;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.EventProcessor;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.RootEventProcessor;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.factory.EventProcessorComponentFactory;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.ActivityEventProcessorConfiguration;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.EventComponentConfiguration;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.GroupingOfEventProcessorsConfiguration;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.ActivityEventProcessorComposerConfigurator;

/**
 * Standard ResponseViewComposer inatantiation. 
 * @author josep
 */
public class BaseActivityEventProcessorComposer extends AutoregistryEventComponentSuppliers implements ActivityEventProcessorComposer{
    Map<String, Object> additionalData = null;
    private ActivityEventProcessorConfiguration config;
    

    public BaseActivityEventProcessorComposer() {
    }

    public BaseActivityEventProcessorComposer(ActivityEventProcessorConfiguration config) {
        this.config = config;
    }

    @Override
    public RootEventProcessor getRootEventProcessor() {
        return this.getRootEventProcessor(config);
    }
    
    @Override
    public RootEventProcessor getRootEventProcessor(ActivityEventProcessorConfiguration configuration) {
        RootEventProcessor processor;
        processor = getRootEventProcessorFromSupplier(configuration);        
        ActivityEventDataModelConverter converter = getActivityEventDataModelConverter(configuration.getEventDataModeConverterConfiguration());
        ActivityEventResponseHandler responseHandler = getActivityEventResponseHandler(configuration.getEventResponseHandlerConfiguration());
        processor.setEventDataHandler(converter);
        processor.setEventResponseGeerator(responseHandler);
        
        for(EventComponentConfiguration ecc: configuration.getEventComponentConfigurationList()){
            EventProcessor ep = getEventProcessor(ecc);
            processor.getEventProcessorList().add(ep);
        }
        return processor;
    }
    
    
    private ActivityEventResponseHandler getActivityEventResponseHandler(EventComponentConfiguration configuration){
        ActivityEventResponseHandler ret;
        registerSuppliersFromConfiguration(configuration);
        ret = EventProcessorComponentFactory.instanceActivityEventResponseHandler(configuration.getFullName());
        return ret;
    }
    
    private RootEventProcessor getRootEventProcessorFromSupplier(EventComponentConfiguration configuration){
        RootEventProcessor ret;
        registerSuppliersFromConfiguration(configuration);
        ret = EventProcessorComponentFactory.instanceRootEventProcessor(configuration.getFullName());
        return ret;
        
    }
    
    private ActivityEventDataModelConverter getActivityEventDataModelConverter(EventComponentConfiguration configuration){
        ActivityEventDataModelConverter ret;
        registerSuppliersFromConfiguration(configuration);
        ret = EventProcessorComponentFactory.instanceActivityEventDataModelConverter(configuration.getFullName());
        return ret;
    }
    
    private EventProcessor getEventProcessor(EventComponentConfiguration configuration){
        EventProcessorComposer composer =  EventProcessorComposerFactory.getEventComposerInstance(configuration);
        EventProcessor processor = composer.getEventProcessor(configuration);
        return processor;
    }
}
