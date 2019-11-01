package com.soapsnake.pattern.structurals.compositepattern;

public class Client {

    public static void main(String[] args) {
        Component c1 = new ConcreteComponent1();   //叶节点1
        Component c2 = new ConcreteComponent2();
        Component c3 = new ConcreteComponent3();

        Component component = new Composite();    //数枝节点
        component.add(c1);
        component.add(c2);
        component.add(c3);
        component.operation();

        component.remove(c2);
        component.operation();
    }
}
