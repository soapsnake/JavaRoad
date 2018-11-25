package com.soapsnake.dubbo.api.service;

/**
 * @author soapsnake
 * @date 2018/10/28
 */
public interface CallbackService {
    void addListener(String key, CallbackListener listener);
}
