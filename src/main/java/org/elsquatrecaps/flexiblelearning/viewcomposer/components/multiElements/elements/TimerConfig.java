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
package org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.elements;

import java.util.Map;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.elements.GenericElementTagAttributesMap;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 *
 * @author josep
 */
public class TimerConfig extends GenericElementTagAttributesMap{
    private static int counter=0;
    public static final String TYPE_ATTR = "data-type"; 
    public static final String TIME_ATTR = "data-time"; 
    public static final String URL_ATTR = "data-url"; 
    public static final String DATA_OBJECT_ATTR = "data-data-object"; 
    public static final String REQUEST_METHOD_ATTR = "data-request-method"; 

    public TimerConfig() {
        this.setAttribute(ID_ATTR, String.format("timer_%d", counter));
        this.setAttribute(TYPE_ATTR, "timer");
        counter++;
    }
    
    public TimerConfig(String id) {
        this.setAttribute(ID_ATTR, id);
        this.setAttribute(TYPE_ATTR, "timer");
    }
    
    @PersistenceConstructor
    protected TimerConfig(Map<String, String> map){        
        super(map);
    }
    
    public void setTime(int time){
        setAttribute(TIME_ATTR, String.valueOf(time));
    }

    public void setUrl(String url){
        setAttribute(URL_ATTR, url);
    }

    public void setDataObjectName(String dataObjectName){
        setAttribute(DATA_OBJECT_ATTR, dataObjectName);
    }

    public void setRequestMethod(String requestMethod){
        setAttribute(REQUEST_METHOD_ATTR, requestMethod);
    }
}
