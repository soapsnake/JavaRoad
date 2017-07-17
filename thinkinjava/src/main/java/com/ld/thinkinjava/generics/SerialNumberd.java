package com.ld.thinkinjava.generics;

/**
 * Created by liudun on 2017/7/16.
 */
public class SerialNumberd extends Decorator {
    private static long counter = 1;
    private final long serialNumber = counter++;

    public SerialNumberd(Basic basic) {
        super(basic);
    }
    public long getSerialNumber(){
        return serialNumber;
    }
}
