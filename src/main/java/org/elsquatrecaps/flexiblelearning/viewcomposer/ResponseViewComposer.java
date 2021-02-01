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

import java.util.Map;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.ResponseViewComponent;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author josep
 */
public interface ResponseViewComposer {
    ModelAndView getResponseView();   
    ModelAndView getResponseView(boolean configureModel);   
    void addComponent(String key, ResponseViewComponent component, ModelAndView model);
    void addAdditionalData(String key, Object aditionalData);
    Object getAdditionalData(String key);
    Map<String, Object> getAdditionalData();
    void initWithAdditionalData(Map<String, Object> aditionalData);
    boolean hasAdditionalData();
    void configModel(ModelAndView model);    
}
