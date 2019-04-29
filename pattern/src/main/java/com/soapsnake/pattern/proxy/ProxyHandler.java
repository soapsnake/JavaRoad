package com.soapsnake.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-29 15:11
 */
public class ProxyHandler implements InvocationHandler {

    private ObjInterface realObj;   //被代理的对象

    ProxyHandler(ObjInterface obj) {
        this.realObj = obj;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("调真实接口之前......");

        method.invoke(realObj, args);

        System.out.println("调真实接口之后......");
        return null;
    }
}
