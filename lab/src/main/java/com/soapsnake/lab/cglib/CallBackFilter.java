package com.soapsnake.lab.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class CallBackFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if (method.getName().equals("test")) {   //如果被代理的方法名称是test的话,那就执行第二个切面逻辑
            return 1;
        } else {
            return 0;   //如果不是那就执行第一个切面逻辑
        }
    }
}
