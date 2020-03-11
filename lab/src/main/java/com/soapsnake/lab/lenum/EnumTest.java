package com.soapsnake.lab.lenum;

/**
 * Created by soapsnake on 2017/5/16.
 */
public class EnumTest {

    public static void main(String[] args) {
        System.out.println("start!");

        System.out.println(TestEnum.CREATE);

        System.out.println(TestEnum.CREATE.name());

        System.out.println(TestEnum.CREATE.toString());

        System.out.println(TestEnum.CREATE.desc.toString());

        System.out.println(TestEnum.CREATE.desc);
    }


}
