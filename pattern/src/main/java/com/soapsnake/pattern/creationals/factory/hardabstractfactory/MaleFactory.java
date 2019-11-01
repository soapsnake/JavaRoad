package com.soapsnake.pattern.creationals.factory.hardabstractfactory;

import com.soapsnake.pattern.creationals.factory.Human;

public class MaleFactory implements HumanFactory {


    //todo 专门生产Male的黄,黑,百色人种
    @Override
    public Human createYellow() {
        return null;
    }

    @Override
    public Human createBlack() {
        return null;
    }

    @Override
    public Human createWhite() {
        return null;
    }
}
