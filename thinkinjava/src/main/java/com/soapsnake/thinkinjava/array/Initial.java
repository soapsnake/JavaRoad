package com.soapsnake.thinkinjava.array;

/**
 * Created by liudun on 2017/6/10.
 */
public class Initial {

    private ArrayTest2 test2 = new ArrayTest2();

    private String test;

    Initial() {
        test2.testProtec();
        System.out.println("没有报空指针");
    }

    public static void main(String[] args) {
        Initial initial = new Initial();
        initial.f();
    }

    public void f() {
        test2.testPack();
        System.out.println("还是没有空指针");
    }

}
