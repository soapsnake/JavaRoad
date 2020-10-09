package com.soapsnake.mydubbo;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-09
 */
public class RpcTester {

    public static void main(String[] args) {
        RpcProxy rpcProxy = new RpcProxy("TestService");

        //调用方式一
        rpcProxy.execute("hello");
        rpcProxy.execute("hello","hahahaha");

        //调用方式二：跟调本地服务有啥分别？？
//        TestService testService = (TestService) rpcProxy.create();
//        testService.hello();
//        testService.hello("hohohoho");
    }
}
