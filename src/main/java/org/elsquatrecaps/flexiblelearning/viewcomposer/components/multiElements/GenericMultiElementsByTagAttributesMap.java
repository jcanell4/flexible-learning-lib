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
package org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.elements.ElementByTagAttributesMap;

/**
 *
 * @author josep
 */
public class GenericMultiElementsByTagAttributesMap extends ArrayList<ElementByTagAttributesMap> implements MultiElementsByTagAttributesMap{
    private String templateName=null;

    public GenericMultiElementsByTagAttributesMap() {
    }
    
    
    public GenericMultiElementsByTagAttributesMap(String templateName) {
        this.templateName = templateName;
    }
    
 
    @Override
    public List<String> getElementAttributesMapList() {
        ArrayList<String> ret= new ArrayList<>();
        
        forEach(new Consumer<ElementByTagAttributesMap>(){
            @Override
            public void accept(ElementByTagAttributesMap t) {
                ret.add(t.getKeysAndValues());
            }
        });
        return ret;
    }

    @Override
    public String getTemplateName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
