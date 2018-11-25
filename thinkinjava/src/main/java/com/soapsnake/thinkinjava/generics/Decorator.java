package com.soapsnake.thinkinjava.generics;

/**
 * Created by liudun on 2017/7/16.
 */
public class Decorator extends Basic {

    protected Basic basic;

    public Decorator(Basic basic) {
        this.basic = basic;
    }

    @Override
    public void set(String val) {
        basic.set(val);
    }

    @Override
    public String get() {
        return basic.get();
    }

}
