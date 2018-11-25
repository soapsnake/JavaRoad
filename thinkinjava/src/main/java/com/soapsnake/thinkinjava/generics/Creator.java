package com.soapsnake.thinkinjava.generics;

/**
 * Created by liudun on 2017/7/9.
 */
public class Creator extends GenericWithCreate<X> {

    @Override
    X create() {
        return new X();    //重写了父类型中的create()方法,因此父类调用create()方法时将会调用这个方法
    }

    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}
