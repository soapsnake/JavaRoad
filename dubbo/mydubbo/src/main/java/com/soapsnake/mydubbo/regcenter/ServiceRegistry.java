package com.soapsnake.mydubbo.regcenter;

/**
 * 
 * Created on 2020-10-09
 */
public interface ServiceRegistry {

    //服务注册
    void registerService(String serviceName, String serviceAddress);

    //服务发现
    String discoverService(String serviceName);
}
