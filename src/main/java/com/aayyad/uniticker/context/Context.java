package com.aayyad.uniticker.context;

public interface Context {
    <T> Context addParam(String paramName, T param);
    <T> T getParam(String paramName);
}
