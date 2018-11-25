package com.soapsnake.dubbo.api.service;

/**
 * @author soapsnake
 * @date 2018/10/31
 */
public interface TestGenericService {

    /**
     * 泛化实现,provider侧根本就没有实现该接口的类,consumer仍可调用
     * @param param
     * @return
     */
    String testGen(String param);
}
