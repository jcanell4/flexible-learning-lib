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
import java.util.function.Consumer;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.elements.ElementByType;

/**
 *
 * @author josep
 */
public class GenericMultiElementsByType extends ArrayList<ElementByType> implements MultiElementsByType{
    private String templateName=null;
    private String typeKey;

    public GenericMultiElementsByType(String templateName, String typeKey) {
        this.typeKey = typeKey;
        this.templateName = templateName;
    }
    
    public GenericMultiElementsByType(String typeKey) {
        this.typeKey = typeKey;
    }

    public GenericMultiElementsByType() {
        this.typeKey = typeKey = "data-type";
    }
    
    @Override
    public String getSelectors() {
        StringBuilder strBuilder = new StringBuilder();
        forEach(new Consumer<ElementByType>(){
            boolean isFirst=true;
            @Override
            public void accept(ElementByType t) {
                if(isFirst){
                    isFirst=false;
                }else{
                    strBuilder.append(" or ");
                }
                strBuilder.append(typeKey);
                strBuilder.append("='");
                strBuilder.append(t.getType());
                strBuilder.append("'");            }            
        });
        return strBuilder.toString();
    }

    @Override
    public String getTemplateName() {
        return this.templateName;
    }
    
}
