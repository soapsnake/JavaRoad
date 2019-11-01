package com.soapsnake.pattern.creationals.factory;

import com.soapsnake.pattern.creationals.factory.hardabstractfactory.FemaleFactory;
import com.soapsnake.pattern.creationals.factory.hardabstractfactory.MaleFactory;
import com.soapsnake.pattern.creationals.factory.simpleabstractfactory.YellowManFactory;

public class Client {

    public static void main(String[] args) {
        Human human = SimpleHumanFactory.createHuman(YellowHuman.class);

        System.out.println(human.getColor());

        System.out.println(human.sayHello());

        Human human1 = new YellowManFactory().createHuman();
        human1.getColor();

        //生产母的的黑人白人黄人
        FemaleFactory femaleFactory = new FemaleFactory();
        femaleFactory.createBlack();
        femaleFactory.createWhite();
        femaleFactory.createYellow();

        //生产公的黑人白人黄人
        MaleFactory maleFactory = new MaleFactory();
        maleFactory.createBlack();
        maleFactory.createWhite();
        maleFactory.createYellow();

    }
}
