package com.soapsnake.pattern.creationals.factory;

public class YellowHuman implements Human {
    @Override
    public String getColor() {
        return "I'm yellow man!";
    }

    @Override
    public String sayHello() {
        return "你好";
    }

    @Override
    public String getSex() {
        return null;
    }
}
