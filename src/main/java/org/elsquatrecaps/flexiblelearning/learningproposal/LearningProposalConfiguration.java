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
package org.elsquatrecaps.flexiblelearning.learningproposal;

import java.util.Map;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.BaseResponseViewMainComponent;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.ResponseViewComponent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.GenericMultiElementsByTagAttributesMap;
import org.elsquatrecaps.flexiblelearning.viewcomposer.components.multiElements.GenericMultiElementsByType;

/**
 *
 * @author josep
 */
@Document
public class LearningProposalConfiguration extends BaseResponseViewMainComponent{
    @Id
    private final String id;

    public LearningProposalConfiguration(String id) {
        this.id = id;
    }
    
    public LearningProposalConfiguration(String Id, String template) {
        super(template);
        this.id = Id;
    }

    @PersistenceConstructor
    protected LearningProposalConfiguration(String id, String templateName, Map<String, 
            Object> configurationDataMap, Map<String, ResponseViewComponent> componentMap, 
            GenericMultiElementsByType links, GenericMultiElementsByType scripts, 
            GenericMultiElementsByType modules, GenericMultiElementsByTagAttributesMap configComponentElements) {
        super(templateName, configurationDataMap, componentMap, links, scripts, modules, configComponentElements);
        this.id = id;
    }


    public String getId() {
        return id;
    }
}
