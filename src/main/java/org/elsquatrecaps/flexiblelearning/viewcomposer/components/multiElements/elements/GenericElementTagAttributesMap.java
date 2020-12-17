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

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 *
 * @author josep
 */
public class GenericElementTagAttributesMap implements ElementByTagAttributesMap{
    public static final String ID_ATTR = "id";
    private Map<String, String> map;

    public GenericElementTagAttributesMap() {
        map = new HashMap<>();
    }
    
    @PersistenceConstructor
    protected GenericElementTagAttributesMap(Map<String, String> map){
        this.map = map;
    }
    
    @Override
    public String getKeysAndValues() {
        StringBuilder strBuilder = new StringBuilder();
  
        
        this.map.forEach(new BiConsumer<String, String>() {
            boolean isFirst=true;
            @Override
            public void accept(String key, String value) {
                if(isFirst){
                    isFirst=false;
                }else{
                    strBuilder.append(", ");
                }
                strBuilder.append(key);
                strBuilder.append("='");
                strBuilder.append(value);
                strBuilder.append("'");
            }
        });
        return strBuilder.toString();
    }

    @Override
    public String getTemplateName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAttribute(String attributeName, String value) {
        this.map.put(attributeName, value);
    }
    
}
