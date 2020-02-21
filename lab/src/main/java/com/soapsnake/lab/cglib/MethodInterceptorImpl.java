package com.soapsnake.lab.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MethodInterceptorImpl implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {

        System.err.println("Before invoke " + method);   //前置增强逻辑
        Object result = proxy.invokeSuper(o, objects);   //执行代理对象的原始逻辑
        System.err.println("After invoke" + method);    //后置增强逻辑
        return result;
    }
}
