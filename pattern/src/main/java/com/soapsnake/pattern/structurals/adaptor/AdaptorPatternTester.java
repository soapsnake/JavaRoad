package com.soapsnake.pattern.structurals.adaptor;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-02 11:25
 */
public class AdaptorPatternTester {

    public static void main(String[] args) {
        TargetInterface target = new TargetObj();

        target.method1();
        target.method2();
        target.method3();
    }
}
