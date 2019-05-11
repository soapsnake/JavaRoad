package com.soapsnake.pattern.structuralPatterns.adaptor;

/**
 *
 * 适配类
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-02 11:24
 */
public class TargetObj implements TargetInterface {

    private TobeAdapte tobeAdapte;

    public TargetObj() {
        tobeAdapte = new TobeAdapte();
    }

    @Override
    public void method1() {
        tobeAdapte.method1();
    }

    @Override
    public void method2() {
        tobeAdapte.method2();
    }

    @Override
    public void method3() {
        System.out.println("TargetObj -> method3");
    }
}
