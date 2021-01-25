/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.elsquatrecaps.flexiblelearning.eventmanager.responses;

/**
 *
 * @author josep
 */
public class Timer {
    private int time=0;
    private String url="";
    private String dataObject=null;
    private String getDataToSend=null;
    private String requestMethod=null;
    private String callableObject=null;

    public Timer() {
    }
    
    
    public Timer(int time, String url) {
        this.time = time;
        this.url = url;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the dataObject
     */
    public String getDataObject() {
        return dataObject;
    }

    /**
     * @param dataObject the dataObject to set
     */
    public void setDataObject(String dataObject) {
        this.dataObject = dataObject;
    }

    /**
     * @return the getDataToSend
     */
    public String getGetDataToSend() {
        return getDataToSend;
    }

    /**
     * @param getDataToSend the getDataToSend to set
     */
    public void setGetDataToSend(String getDataToSend) {
        this.getDataToSend = getDataToSend;
    }

    /**
     * @return the requestMethod
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * @param requestMethod the requestMethod to set
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * @return the callableObject
     */
    public String getCallableObject() {
        return callableObject;
    }

    /**
     * @param callableObject the callableObject to set
     */
    public void setCallableObject(String callableObject) {
        this.callableObject = callableObject;
    }
    
}
