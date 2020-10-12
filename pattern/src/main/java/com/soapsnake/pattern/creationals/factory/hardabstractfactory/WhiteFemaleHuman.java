package com.soapsnake.pattern.creationals.factory.hardabstractfactory;

import com.soapsnake.pattern.creationals.factory.Human;

public class WhiteFemaleHuman implements Human {
    @Override
    public String getColor() {
        return null;
    }

    @Override
    public String sayHello() {
        return null;
    }

    @Override
    public String getSex() {
        return "Femal";
    }
}
