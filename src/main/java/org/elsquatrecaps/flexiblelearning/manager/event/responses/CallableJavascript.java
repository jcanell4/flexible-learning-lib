package org.elsquatrecaps.flexiblelearning.manager.event.responses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author josep
 */
public class CallableJavascript{
    public String name=null;
    public Map<String, Serializable> params=null;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the params
     */
    public Map<String, Serializable> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParam(String paramKey, Serializable paramValue) {
        if(params==null){
            params = new HashMap<>();
        }
        params.put(paramKey, paramValue);
    }
    
    /**
     * @param params the params to set
     */
    public void setParams(Map<String, Serializable> params) {
        this.params = params;
    }
}
