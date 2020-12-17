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

import org.elsquatrecaps.flexiblelearning.viewcomposer.components.ResponseViewConfigData;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author josep
 */
@Document
public class LearningProposalConfiguration<T extends ResponseViewConfigData> {
    @Id
    private final String id;
    private final T responseViewConfigData;

    @PersistenceConstructor
    public LearningProposalConfiguration(String id, T responseViewConfigData) {
        this.id = id;
        this.responseViewConfigData = responseViewConfigData;
    }

    public LearningProposalConfiguration(String id) {
        this.id = id;
        this.responseViewConfigData = null;
    }

    public String getId() {
        return id;
    }

    public T getResponseViewConfigData() {
        return responseViewConfigData;
    }
}
