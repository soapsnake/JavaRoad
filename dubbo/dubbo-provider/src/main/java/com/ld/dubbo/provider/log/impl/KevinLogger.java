package com.ld.dubbo.provider.log.impl;

import com.ld.dubbo.provider.log.Logger;

/**
 * @author soapsnake
 * @date 2018/10/29
 */
public class KevinLogger implements Logger {


    @Override
    public void log() {
        System.out.println("kevin logger here!!");
    }
}
