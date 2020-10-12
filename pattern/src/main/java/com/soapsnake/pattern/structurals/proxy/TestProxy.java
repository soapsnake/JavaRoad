package com.soapsnake.pattern.structurals.proxy;


/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-29 15:10
 */
public class TestProxy {

    public static void main(String[] args) {

        ObjInterface objInterface = new RealObj();

        //接口的实现对象不知道照样可以代理
        //这个其实就是
        ProxyHandler<ObjInterface> handler = new ProxyHandler<>(() -> {
        }, new SomeInterceptor());
        ObjInterface proxy = (ObjInterface) handler.getProxy();
        proxy.originMethod();
    }
}
