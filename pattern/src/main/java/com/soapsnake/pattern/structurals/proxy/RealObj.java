package com.soapsnake.pattern.structurals.proxy;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-29 15:02
 */
public class RealObj implements ObjInterface  {

    private String name;
    private int age;

    @Override
    public void originMethod() {
        System.out.println("RealObj's origin methond has been invoked!!!!");
    }

}
