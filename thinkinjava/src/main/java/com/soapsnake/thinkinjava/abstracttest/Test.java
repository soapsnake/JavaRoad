package com.soapsnake.thinkinjava.abstracttest;

/**
 * Created by soapsnake on 2017/6/13.
 */
public class Test {

    public static void main(String[] args) {
        //不能实例化抽象类
//        Instrument4 instrument4 = new Instrument4();


        //接口中的字段自带static final关键字
//        Months.JAN = 2;


        //即使是静态常量,也可以是可变的
        System.out.println(Months.ARG1);
    }
}
