package com.ld.thinkinjava.generics;

/**
 * Created by liudun on 2017/7/16.
 */
public class DecoratorTest {

    public static void main(String[] args) {
        TimeStamped timeStamped = new TimeStamped(new Basic());
        TimeStamped timeStamped1 = new TimeStamped(new SerialNumberd(new Basic()));

        System.out.println(timeStamped.getStamp());
        System.out.println(timeStamped1.getStamp());

        SerialNumberd serialNumberd = new SerialNumberd(new Basic());
        SerialNumberd serialNumberd1 = new SerialNumberd(new TimeStamped(new Basic()));
        System.out.println(serialNumberd.getSerialNumber());
        System.out.println(serialNumberd.getSerialNumber());

    }
}
