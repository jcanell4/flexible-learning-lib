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
package org.elsquatrecaps.flexiblelearning.eventcomposer;

import java.lang.reflect.InvocationTargetException;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.ActivityEventProcessorConfiguration;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.EventProcessorComposerConfigurator;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.EventComponentConfiguration;
import org.elsquatrecaps.flexiblelearning.eventcomposer.components.ActivityEventProcessorComposerConfigurator;

/**
 *
 * @author josep
 */
public class EventProcessorComposerFactory {
    public static ActivityEventProcessorComposer getActivityEventComposerInstance(ActivityEventProcessorConfiguration configuration){    
        ActivityEventProcessorComposer ret=null;
        ActivityEventProcessorComposerConfigurator annotation = configuration.getClass().getAnnotation(ActivityEventProcessorComposerConfigurator.class);
        if(annotation!=null){
            try {
                ret = (ActivityEventProcessorComposer) annotation.eventProcessorComposerClass().getConstructor(ActivityEventProcessorConfiguration.class).newInstance(configuration);
            } catch (InstantiationException | SecurityException | NoSuchMethodException | 
                    IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            throw new RuntimeException("UNKNOWN_EVENT_PROCESSOR_COMPOSER_FOR_THIS_COMPONENT");
        }
        
        return ret;
    }
    
    public static EventProcessorComposer getEventComposerInstance(EventComponentConfiguration configuration){    
        EventProcessorComposer ret=null;
        EventProcessorComposerConfigurator annotation = configuration.getClass().getAnnotation(EventProcessorComposerConfigurator.class);
        if(annotation!=null){
            try {
                ret = (EventProcessorComposer) annotation.eventProcessorComposerClass().getConstructor(EventComponentConfiguration.class).newInstance(configuration);
            } catch (InstantiationException | SecurityException | NoSuchMethodException | 
                    IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            throw new RuntimeException("UNKNOWN_EVENT_PROCESSOR_COMPOSER_FOR_THIS_COMPONENT");
        }
        
        return ret;
    }
}
