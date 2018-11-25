package com.soapsnake.spi.demo.service.impl;

import com.soapsnake.spi.demo.service.ISayName;

public class SayChineseName implements ISayName {
    @Override
    public void say() {
        System.out.println("服部千军");
    }
}
