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
package org.elsquatrecaps.flexiblelearning.viewcomposer.components;

import java.util.Map;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.GenericMultiElementsByTagAttributesMap;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.GenericMultiElementsByType;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 *
 * @author josep
 */
public class BaseResponseViewComponent extends BaseResponseViewConfigData implements ResponseViewComponent   {
    private String fragmentName;   

    public BaseResponseViewComponent(String templateName, String fragmentName) {
        super(templateName);
        this.fragmentName = fragmentName;
    }
    
   @PersistenceConstructor
   protected BaseResponseViewComponent(String templateName, String fragmentName, 
           Map<String, ConfigurationData> configurationDataMap, 
           Map<String, ResponseViewConfigData> componentMap, GenericMultiElementsByType links, 
           GenericMultiElementsByType scripts, GenericMultiElementsByType modules, 
           GenericMultiElementsByTagAttributesMap configComponentElements) {
        super(templateName, configurationDataMap, componentMap, links, scripts, modules, configComponentElements);
        this.fragmentName = fragmentName;
    }

    /**
     * @return the fragmentName
     */
    @Override
    public String getFragmentName() {
        return fragmentName;
    }
}
