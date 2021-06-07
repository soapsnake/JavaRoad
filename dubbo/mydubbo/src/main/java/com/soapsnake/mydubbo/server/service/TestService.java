package com.soapsnake.mydubbo.server.service;

import com.soapsnake.mydubbo.annotate.RpcService;

/**
 * 
 * Created on 2020-10-10
 */
@RpcService("TestService")
public interface TestService {

    void someFunction(String content);

}
