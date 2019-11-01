package com.soapsnake.pattern.creationals.factory;

public class BlackHuman implements Human {
    @Override
    public String getColor() {
        return "I'm black human";
    }

    @Override
    public String sayHello() {
        return "x&*^*^&T";
    }

    @Override
    public String getSex() {
        return null;
    }
}
