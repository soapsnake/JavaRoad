package com.soapsnake.spi.demo.service.impl;

import com.soapsnake.spi.demo.service.ISayName;

public class SayEnglishName implements ISayName {
    @Override
    public void say() {
        System.out.println("soapsnake");
    }
}
