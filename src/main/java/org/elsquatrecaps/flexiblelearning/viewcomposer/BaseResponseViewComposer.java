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

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.ConfigurationData;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.ResponseViewComponent;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.ResponseViewConfigData;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.GenericMultiElementsByTagAttributesMap;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.GenericMultiElementsByType;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author josep
 */
public class BaseResponseViewComposer implements ResponseViewComposer{
    private ResponseViewConfigData config;

    public BaseResponseViewComposer() {
    }

    public BaseResponseViewComposer(ResponseViewConfigData config) {
        this.config = config;
    }
    
    @Override
    public ModelAndView getResponseView(boolean configureModel) {
        ModelAndView model;
        
        model = new ModelAndView(config.getTemplateName());
        if(configureModel){
            this.configModel(model);
        }
       
        return model;        
    }
    
    @Override
    public ModelAndView getResponseView() {
        return this.getResponseView(true);
    }
    
    public void addComponent(String key, ResponseViewConfigData component, ModelAndView model){
        model.addObject(key, component);
        ResponseViewComposer composer = ResponseViewComposerfactory.getResponseViewComposerInstance(component);
        composer.configModel(model);        
    }
    
    public void configModel(ModelAndView model){
          
        this.configModelFromElements(model);
        this.configModelFromConfigurarionData(model);
        this.configModelFromComponents(model);
    }
    
    protected void configModelFromElements(ModelAndView model){
        this.configElementsBySelector(model, "links", config.getLinks());
        this.configElementsBySelector(model, "scripts", config.getScripts());
        this.configElementsBySelector(model, "modules", config.getModules());
        this.configElementsByAttributeMap(model, "mefConfigComponents", config.getConfigComponentElements());
    }
    
    protected void configModelFromConfigurarionData(ModelAndView model){
        config.getConfigurationDataMap().forEach(new BiConsumer<String, ConfigurationData>() {
            @Override
            public void accept(String t, ConfigurationData u) {
                model.addObject(t, u);
            }
        } );        
    }
    
    public void configModelFromComponents(ModelAndView model){
        config.getComponentMap().forEach(new BiConsumer<String, ResponseViewConfigData>(){
            @Override
            public void accept(String t, ResponseViewConfigData u) {
                model.addObject(t, u);
                ResponseViewComposer composer = ResponseViewComposerfactory.getResponseViewComposerInstance(u);
                composer.configModel(model);
            }        
        });
    }
    
    protected void configElementsByAttributeMap(ModelAndView model, String keyElement, GenericMultiElementsByTagAttributesMap elements){
        if(!elements.getElementAttributesMapList().isEmpty()){
            Map<String, Object> map = model.getModel();
            if(map.containsKey(keyElement)){
                ((GenericMultiElementsByTagAttributesMap) map.get(keyElement)).addAll(elements);
            }else{
                model.addObject(keyElement, elements.getElementAttributesMapList());            
            }        
        }
    }

    protected void configElementsBySelector(ModelAndView model, String keyElement, GenericMultiElementsByType elements){
        if(!elements.getSelectors().isEmpty()){
            Map<String, Object> map = model.getModel();
            if(map.containsKey(keyElement)){
                ((GenericMultiElementsByType) map.get(keyElement)).addAll(elements);
            }else{
                model.addObject(keyElement, elements.getSelectors());            
            }  
        }
    }

    public ResponseViewConfigData getConfig() {
        return config;
    }

}
