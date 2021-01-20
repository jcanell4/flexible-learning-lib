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
package org.elsquatrecaps.flexiblelearning.viewcomposer;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.ResponseViewComponentConfigurator;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.ResponseViewConfigData;


/**
 *
 * @author josep
 */
public class ResponseViewComposerfactory {
    
    public static ResponseViewComposer getResponseViewComposerInstance(ResponseViewConfigData component){
        ResponseViewComposer ret=null;
        ResponseViewComponentConfigurator annotation = component.getClass().getAnnotation(ResponseViewComponentConfigurator.class);
        if(annotation!=null){
            try {
                ret = (ResponseViewComposer) annotation.responseViewComposerClass().getConstructor(ResponseViewConfigData.class).newInstance(component);
            } catch (InstantiationException | SecurityException | NoSuchMethodException | 
                    IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            throw new RuntimeException("UNKNOWN_RESPONSE_VIEW_COMPOSER_FOR_THIS_COMPONENT");
        }
        
        return ret;
    }    
}
