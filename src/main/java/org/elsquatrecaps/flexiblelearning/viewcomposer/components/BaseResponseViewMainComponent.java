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

import java.util.HashMap;
import java.util.Map;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.GenericMultiElementsByTagAttributesMap;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.GenericMultiElementsByType;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author josep
 */
@Document
@ResponseViewComponentConfigurator
public class BaseResponseViewMainComponent implements ResponseViewComponent {

    private String templateName;
    private final Map<String, Object> configurationDataMap;
    private final Map<String, ResponseViewComponent> componentMap;
    private final GenericMultiElementsByType links;
    private final GenericMultiElementsByType scripts;
    private final GenericMultiElementsByType modules;
    private final GenericMultiElementsByTagAttributesMap configComponentElements;

    public BaseResponseViewMainComponent() {
        this.componentMap = new HashMap<>();
        this.configurationDataMap = new HashMap<>();
        this.links = new GenericMultiElementsByType();
        this.scripts = new GenericMultiElementsByType();
        this.modules = new GenericMultiElementsByType();
        this.configComponentElements = new GenericMultiElementsByTagAttributesMap();
    }

    public BaseResponseViewMainComponent(String template) {
        this.templateName = template;
        this.componentMap = new HashMap<>();
        this.configurationDataMap = new HashMap<>();
        this.links = new GenericMultiElementsByType();
        this.scripts = new GenericMultiElementsByType();
        this.modules = new GenericMultiElementsByType();
        this.configComponentElements = new GenericMultiElementsByTagAttributesMap();
    }

    @PersistenceConstructor
    protected BaseResponseViewMainComponent(String templateName, Map<String, 
            Object> configurationDataMap, Map<String, ResponseViewComponent> componentMap, 
            GenericMultiElementsByType links, GenericMultiElementsByType scripts, 
            GenericMultiElementsByType modules, GenericMultiElementsByTagAttributesMap configComponentElements) {
        this.templateName = templateName;
        this.componentMap = componentMap;
        this.configurationDataMap = configurationDataMap;
        this.links = links;
        this.scripts = scripts;
        this.modules = modules;
        this.configComponentElements = configComponentElements;
    }

    /**
     * @return the templateName
     */
    @Override
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param baseTemplate the templateName to set
     */
    @Override
    public void setTemplateName(String baseTemplate) {
        this.templateName = baseTemplate;
    }

    /**
     * @return the configurationDataMap
     */
    @Override
    public Map<String, Object> getConfigurationDataMap() {
        return configurationDataMap;
    }

    /**
     * @return the componentMap
     */
    @Override
    public Map<String, ResponseViewComponent> getComponentMap() {
        return componentMap;
    }

    /**
     * @return the links
     */
    public GenericMultiElementsByType getLinks() {
        return links;
    }

    /**
     * @return the scripts
     */
    public GenericMultiElementsByType getScripts() {
        return scripts;
    }

    /**
     * @return the modules
     */
    public GenericMultiElementsByType getModules() {
        return modules;
    }

    @Override
    public GenericMultiElementsByTagAttributesMap getConfigComponentElements() {
        return configComponentElements;
    }
}
