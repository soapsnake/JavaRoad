package com.soapsnake.thinkinjava.generics;

import java.util.Date;

/**
 * Created by soapsnake on 2017/7/16.
 */
public class TimeStamped extends Decorator {

    private final long timeStamp;

    public TimeStamped(Basic basic) {
        super(basic);
        timeStamp = new Date().getTime();
    }

    public long getStamp() {
        return timeStamp;
    }
}
