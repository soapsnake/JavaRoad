package com.soapsnake.algorithms.bitalgo.operators;

public class OperatorsTest {

    public static void main(String[] args) {
        int i = 1;


        //向右移位值会减少
        System.out.println(i >> 1);
        //向左移位值会增加
        System.out.println(i << 1);

        int j = -10;
        //负数反过来
        System.out.println(j >> 2);
        System.out.println(j << 2);

        Integer i1 = 2;
        Integer i2 = 1;
        System.out.println(i1.compareTo(i2));

    }
}
