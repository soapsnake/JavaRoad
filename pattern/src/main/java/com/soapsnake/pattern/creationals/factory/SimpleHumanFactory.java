package com.soapsnake.pattern.creationals.factory;

public class SimpleHumanFactory {

    private SimpleHumanFactory() {
    }

    public static <T extends Human> T createHuman(Class<T> humanType) {
        try {
            return humanType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


}
