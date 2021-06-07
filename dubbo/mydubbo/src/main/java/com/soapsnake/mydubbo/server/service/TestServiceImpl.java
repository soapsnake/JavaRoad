package com.soapsnake.mydubbo.server.service;

/**
 * 
 * Created on 2020-10-10
 */
public class TestServiceImpl implements TestService {
    @Override
    public void someFunction(String content) {
        System.out.println("TestServiceImpl::someFunction=>" + content);
    }
}
