package com.aayyad.uniticker.context;

import java.util.HashMap;
import java.util.Map;

public class ContextImpl implements Context {
    private Map<String, Object> params;

    public ContextImpl() {
        this.params = new HashMap<String, Object>();
    }

    public <T> Context addParam(String paramName, T param) {
        params.put(paramName, param);
        return this;
    }

    public <T> T getParam(String paramName) {
        return (T) params.get(paramName);
    }
}
