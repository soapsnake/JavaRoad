package com.ld.spi.demo.service.impl;

import com.ld.spi.demo.service.ISayName;

public class SayEnglishName implements ISayName {
    @Override
    public void say() {
        System.out.println("soapsnake");
    }
}
