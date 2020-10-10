package com.soapsnake.mydubbo.server.service;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-10
 */
public class TestServiceImpl implements TestService {
    @Override
    public void someFunction(String content) {
        System.out.println("TestServiceImpl::someFunction=>" + content);
    }
}
