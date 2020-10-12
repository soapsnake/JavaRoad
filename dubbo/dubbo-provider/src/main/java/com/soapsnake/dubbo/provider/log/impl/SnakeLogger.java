package com.soapsnake.dubbo.provider.log.impl;

import com.soapsnake.dubbo.provider.log.Logger;

/**
 * @author soapsnake
 * @date 2018/10/29
 */
public class SnakeLogger implements Logger {
    @Override
    public void log() {
        System.out.println("this is snakeLogger!!!!can log snake");
    }
}
