package com.soapsnake.pattern.creationals.singleton;

public class SingletonClass {

    private static SingletonClass singletonClass;

    private SingletonClass(){}

    public static synchronized SingletonClass getInstance() {
        if (null == singletonClass) {
            singletonClass = new SingletonClass();
        }
        return singletonClass;
    }

    public static SingletonClass getInstanceDoubleCheck() {
        if (null == singletonClass) {   //一步检测
            synchronized (SingletonClass.class) {  //只有为null时才加锁
                if (null == singletonClass) {  //二步检测
                    singletonClass = new SingletonClass();
                }
            }
        }
        return singletonClass;
    }
}
