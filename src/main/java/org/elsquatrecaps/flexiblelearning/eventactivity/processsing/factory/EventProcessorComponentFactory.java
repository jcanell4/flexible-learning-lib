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
package org.elsquatrecaps.flexiblelearning.eventactivity.processsing.factory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.ActivityEventDataModelConverter;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.ActivityEventMainPipe;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.ActivityEventResponseHandler;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.EcoEventPorcessor;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.EventDataToProcessConverter;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.EventProcessor;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.RootEventProcessor;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.SimpleMapActivityEventDataConverter;
import org.elsquatrecaps.flexiblelearning.eventactivity.processsing.VoidEventDataConverter;

/**
 *
 * @author josep
 */
public class EventProcessorComponentFactory {
    private static Set<String> registeredCollections = new HashSet<>();
    private static Map<String,Supplier<EventProcessor>> eventProcessorSupplier = new HashMap<>();
    private static Map<String,Supplier<RootEventProcessor>> rootEventProcessorSupplier = new HashMap<>();
    private static Map<String,Supplier<ActivityEventDataModelConverter>> activityEventConverterSupplier = new HashMap<>();
    private static Map<String,Supplier<EventDataToProcessConverter>> eventDataToProcessConverterSupplier = new HashMap<>();
    private static Map<String,Supplier<ActivityEventResponseHandler>> activityEventResponseHandlerSupplier = new HashMap<>();
    private static boolean registryUpdated = false;

    public static void registerEventProcessorSupplier(String key, Supplier<EventProcessor> supplier){
        if(!registryUpdated){
            registerSuppliers();
        }
        eventProcessorSupplier.put(key, supplier);
    }

    public static void registerRootEventProcessorSupplier(String key, Supplier<RootEventProcessor> supplier){
        if(!registryUpdated){
            registerSuppliers();
        }
        rootEventProcessorSupplier.put(key, supplier);
    }

    public static void registerActivityEventDataModelConverterSupplier(String key, Supplier<ActivityEventDataModelConverter> supplier){
        if(!registryUpdated){
            registerSuppliers();
        }
        activityEventConverterSupplier.put(key, supplier);
    }

    public static void registerEventDataToProcessConverterSupplier(String key, Supplier<EventDataToProcessConverter> supplier){
        if(!registryUpdated){
            registerSuppliers();
        }
        eventDataToProcessConverterSupplier.put(key, supplier);
    }
    
    public static void registerActivityEventResponseHandlerSupplier(String key, Supplier<ActivityEventResponseHandler> supplier){
        if(!registryUpdated){
            registerSuppliers();
        }
        activityEventResponseHandlerSupplier.put(key, supplier);
    }
    
    public static RootEventProcessor instanceRootEventProcessor(String type){
        return rootEventProcessorSupplier.containsKey(type)?rootEventProcessorSupplier.get(type).get():null;
    }

    public static EventProcessor instanceEventProcessor(String type){
        return eventProcessorSupplier.containsKey(type)?eventProcessorSupplier.get(type).get():null;
    }

    public static ActivityEventDataModelConverter instanceActivityEventDataModelConverter(String type){
        return activityEventConverterSupplier.containsKey(type)?activityEventConverterSupplier.get(type).get():null;
    }

    public static EventDataToProcessConverter instanceEventDataToProcessConverter(String type){
        return eventDataToProcessConverterSupplier.containsKey(type)?eventDataToProcessConverterSupplier.get(type).get():null;
    }

    public static ActivityEventResponseHandler instanceActivityEventResponseHandler(String type){
        return activityEventResponseHandlerSupplier.containsKey(type)?activityEventResponseHandlerSupplier.get(type).get():null;
    }
    
    public static boolean isCollectionRegistered(String collection){
        return registeredCollections.contains(collection);
    }

    public static void addCollectionRegisterd(String collection){
        registeredCollections.add(collection);
    }

    public static void registerSuppliers(){
        eventProcessorSupplier.put("default.EcoEventProcessor", EcoEventPorcessor::new);
        eventDataToProcessConverterSupplier.put("default.VoidEventDataConverter", VoidEventDataConverter::new);
        rootEventProcessorSupplier.put("default.ActivityEventMainPipe", ActivityEventMainPipe::new);
        activityEventConverterSupplier.put("default.SimpleMapActivityEventDataConverter", SimpleMapActivityEventDataConverter::new);
        registeredCollections.add("default");
    }

}
