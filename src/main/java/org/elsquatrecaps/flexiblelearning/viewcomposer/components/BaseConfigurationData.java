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

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author josep
 */
public class BaseConfigurationData implements ConfigurationData{

    @Override
    public <T> void set(String property, T data) {
        boolean hasMethod=true;
        try {
            this.getClass().getMethod(getSetMethodName(property), data.getClass()).invoke(this, data);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            hasMethod = false;
        }
        if(!hasMethod){
            try {
                this.getClass().getField(property).set(this, data);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    public<T> T get(String property, Class<T> cl) {
        T ret = null;
        boolean hasMethod=true;
        try {
            ret = (T) this.getClass().getMethod(getSetMethodName(property)).invoke(this);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            hasMethod = false;
        }
        
        if(!hasMethod && cl.isAssignableFrom(Boolean.class)){
            hasMethod = true;
            try {
               ret = (T) this.getClass().getMethod(getIsMethodName(property)).invoke(this);
            } catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
                hasMethod = false;
            }
        }
   
        if(!hasMethod){
            try {
                ret = (T) this.getClass().getField(property).get(this);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
        return ret;
    }
    
    private String getSetMethodName(String attr){
        return getSetGetName("set", attr);
    }
    
    private String getGetMethodName(String attr){
        return getSetGetName("get", attr);
    }
    
    private String getIsMethodName(String attr){
        return getSetGetName("is", attr);
    }
    
    private String getSetGetName(String prefixx, String attr){
        StringBuilder strb = new StringBuilder();
        strb.append(prefixx);
        strb.append(attr.substring(0, 1).toUpperCase());
        strb.append(attr.substring(1));
        return strb.toString();
    }
    
}
