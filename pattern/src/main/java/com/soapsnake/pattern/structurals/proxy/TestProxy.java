package com.soapsnake.pattern.structurals.proxy;


import java.lang.reflect.Proxy;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-29 15:10
 */
public class TestProxy {

    public static void main(String[] args) {
        ObjInterface real = new RealObj();

        ProxyHandler handler = new ProxyHandler(real);
        ObjInterface proxy = (ObjInterface) Proxy.newProxyInstance(
                                    ObjInterface.class.getClassLoader(),
                                    //注意这里的getInterfaces不能用接口的.class来取,否则会报错cast异常
                                    real.getClass().getInterfaces(),
                                    handler);

        proxy.originMethod();
    }
}
