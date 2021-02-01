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

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.GenericMultiElementsByTagAttributesMap;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.GenericMultiElementsByType;
import org.springframework.web.servlet.ModelAndView;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.ResponseViewComponent;

/**
 * Standard ResponseViewComposer inatantiation. 
 * @author josep
 */
public class BaseResponseViewComposer implements ResponseViewComposer{
    Map<String, Object> additionalData = null;
    private ResponseViewComponent config;

    public BaseResponseViewComposer() {
    }

    public BaseResponseViewComposer(ResponseViewComponent config) {
        this.config = config;
    }
    
    /**
     * Obté el ModelAndView corresponent i opcionalment, ja configurat a partir de les
 dades extretes del ResponseViewComponent
     * @param configureModel
     * @return 
     */
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
    
    @Override
    public void addComponent(String key, ResponseViewComponent component, ModelAndView model){
        model.addObject(key, component);
        ResponseViewComposer composer = ResponseViewComposerfactory.getResponseViewComposerInstance(component);
        composer.configModel(model);        
    }
    
    /**
     * Configura el model amb tots els elemnts de configuració continguts a 
 l'atribut config (de tipus ResponseViewComponent).
     * @param model 
     */
    @Override
    public void configModel(ModelAndView model){
          
        this.configModelFromElements(model);
        this.configModelFromConfigurarionData(model);
        this.configModelFromComponents(model);
    }
    
    /**
     * Configura el model a partir dels elements de tipus link. script, module i ConfigComponentElements
     * continguts a la propietat config.
     * @param model 
     */
    protected void configModelFromElements(ModelAndView model){
        this.configElementsBySelector(model, "links", config.getLinks());
        this.configElementsBySelector(model, "scripts", config.getScripts());
        this.configElementsBySelector(model, "modules", config.getModules());
        this.configElementsByAttributeMap(model, "mefConfigComponents", config.getConfigComponentElements());
    }
    
    /**
     * Configura el model a partir de l'objecte de configuració del component principal 
     * contingut a l'atribut config.
     * @param model 
     */
    protected void configModelFromConfigurarionData(ModelAndView model){
        config.getConfigurationDataMap().forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(String t, Object u) {
                model.addObject(t, u);
            }
        } );        
    }
    
    /**
     * Configura el model a partir dels coponents adicionals 
     * continguts a l'atribut config.
     * @param model 
     */
    public void configModelFromComponents(ModelAndView model){
        config.getComponentMap().forEach(new BiConsumer<String, ResponseViewComponent>(){
            @Override
            public void accept(String t, ResponseViewComponent u) {
                model.addObject(t, u);
                ResponseViewComposer composer = ResponseViewComposerfactory.getResponseViewComposerInstance(u);
                if(hasAdditionalData()){
                    composer.initWithAdditionalData(getAdditionalData());
                }
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
                model.addObject(keyElement, elements);            
            }  
        }
    }

    /**
     * obté el valor de l'atribut config
     * @return 
     */
    public ResponseViewComponent getConfig() {
        return config;
    }

    @Override
    public void addAdditionalData(String key, Object aditionalData) {
        if(this.additionalData==null){
            this.additionalData = new HashMap<>();
        }
        this.additionalData.put(key, aditionalData);
    }

    @Override
    public Object getAdditionalData(String key) {
        Object ret = null;
        if(this.additionalData!=null){
            ret = this.additionalData.get(key);
        }
        return ret;
    }

    @Override
    public Map<String, Object> getAdditionalData() {
        return this.additionalData;
    }

    @Override
    public void initWithAdditionalData(Map<String, Object> aditionalData) {
        this.additionalData = aditionalData;
    }

    @Override
    public boolean hasAdditionalData() {
        return this.additionalData!=null;
    }
}
