package com.soapsnake.pattern.structurals.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-29 15:11
 */
public class ProxyHandler<T> implements InvocationHandler {
    //现在,任意的Interface都可以用这个handler来制作proxy了
    private T realObj;   //被代理的对象
    private Interceptor interceptor;

    //第一个需要改动的地方,需要把被代理对象搞进来
    ProxyHandler(T obj, Interceptor interceptor) {
        this.realObj = obj;
        this.interceptor = interceptor;
    }

    @Override
    //第二个改动的地方,需要重写invoke方法
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        interceptor.before();
        method.invoke(realObj, args);
        interceptor.after();
        return null;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                //注意这里的getInterfaces不能用接口的.class来取,否则会报错cast异常
                this.realObj.getClass().getInterfaces(),  //获取对象的接口,如果对象没有接口那么就会有问题的吧
                this);
    }
}
