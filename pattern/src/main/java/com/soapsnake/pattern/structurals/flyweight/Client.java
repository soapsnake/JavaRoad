package com.soapsnake.pattern.structurals.flyweight;

public class Client {

    static FlavorFactory factory = FlavorFactory.getInstance();

    private static void addOrder(String flavor) {
        factory.getOrder(flavor);
    }

    /**
     * 享元模式,这个实现是有点问题的
     *
     * @param args
     */
    public static void main(String[] args) {
        addOrder("美式咖啡");
        addOrder("摩卡");
        addOrder("香草");
        addOrder("猫屎");
        addOrder("摩卡");
        addOrder("摩卡");
        addOrder("摩卡");
        addOrder("摩卡");
        addOrder("摩卡");
        addOrder("摩卡");
        addOrder("摩卡");
        addOrder("摩卡");


        System.out.println(FlavorFactory.size());

    }


}
