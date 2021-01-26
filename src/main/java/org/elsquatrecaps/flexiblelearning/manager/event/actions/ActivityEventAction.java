/*
 * Copyright 2021 Grup de millora MetFlex.
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
package org.elsquatrecaps.flexiblelearning.manager.event.actions;

import java.util.Map;
import org.elsquatrecaps.flexiblelearning.manager.event.responses.EventResponse;

/**
 *
 * @author josep
 */
public class ActivityEventAction {
    private ActivityEventActionDataAdapter adapter;
    private ActivityEventActionEngine engine;

    public ActivityEventAction() {
    }

    public ActivityEventAction(ActivityEventActionDataAdapter adapter, ActivityEventActionEngine engine) {
        this.adapter = adapter;
        this.engine = engine;
    }
    
    public EventResponse process(Map<String, String> eventData){
        EventResponse ret=null;
        
        return ret;
    }
}
