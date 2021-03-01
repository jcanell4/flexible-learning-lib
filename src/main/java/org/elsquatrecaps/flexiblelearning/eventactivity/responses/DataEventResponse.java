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

/**
 *
 * @author josep
 */
public class DataEventResponse implements EventResponseData{
    private CallableJavascript onReciveCallable;    
    private ResponseProcessor onReciveProcessor;    

    public DataEventResponse() {
        this.onReciveCallable = null;
    }

    public DataEventResponse(CallableJavascript onReciveCallable) {
        this.onReciveCallable = onReciveCallable;
    }
    
     /**
     * @return the onReciveCallable
     */
    public CallableJavascript getOnReciveCallable() {
        return onReciveCallable;
    }

    /**
     * @param onReciveCallable the onReciveCallable to set
     */
    public void setOnReciveCallable(CallableJavascript onReciveCallable) {
        this.onReciveCallable = onReciveCallable;
    }

    public ResponseProcessor getOnReciveProcessor() {
        return onReciveProcessor;
    }

    public void setOnReciveProcessor(ResponseProcessor onReciveProcessor) {
        this.onReciveProcessor = onReciveProcessor;
    }
}
