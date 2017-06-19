package com.ld.thinkinjava.abstracttest;

/**
 * Created by liudun on 2017/6/14.
 */
public class Parent {
    private String name;

    Parent(){
        System.out.println("parent constructor");
        isNew();
    }

    void isNew(){
        this.name = "parent";
        System.out.println(this.name);
    }

}
