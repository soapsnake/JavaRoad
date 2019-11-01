package com.soapsnake.pattern.creationals.factory;

public class WhiteHuman implements Human {
    @Override
    public String getColor() {
        return "I'm white";
    }

    @Override
    public String sayHello() {
        return "hello!!!";
    }

    @Override
    public String getSex() {
        return null;
    }
}
