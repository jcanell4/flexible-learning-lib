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
package org.elsquatrecaps.flexiblelearning.eventactivity.responses;

import java.io.Serializable;
import org.elsquatrecaps.flexiblelearning.eventactivity.responses.ShowAlertProcessor.Response;

/**
 *
 * @author josep
 */
public class ShowAlertProcessor extends ResponseProcessor<Response>{
    private static final String NAME = "ShowAlert";

    public ShowAlertProcessor() {
        super(NAME);
        this.setResponse(new Response());
    }
    
    public ShowAlertProcessor(String title, String message) {
        super(NAME);
        this.setResponse(new Response(title, message));
    }
    
    public void setTitle(String title){
        this.response.title = title;
    }

    public String getTitle(){
        return this.getResponse().title;
    }
    
    public void setMessage(String message){
        this.response.message = message;
    }

    public String getMessage(){
        return this.getResponse().message;
    }
    
    public class Response implements Serializable{
        public String title;
        public String message;

        public Response() {
        }
        
        public Response(String title, String message) {
            this.title = title;
            this.message = message;
        }
    }
    
}
