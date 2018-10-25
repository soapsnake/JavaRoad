package com.ld.thinkinjava.inherit;

/**
 * Created by liudun on 2017/7/27.
 */
public class Parent {

    public String parent1;

    public String parent2;

    private String priParent;

    public Parent() {
        this.parent1 = "parent1";
        this.parent2 = "parent2";
        this.priParent = "privateParent";

        System.out.println("父构造方法被调用,parent1被赋值为: " + parent1);
    }

    public void parentShow() {
        System.out.println("parent的parentShow方法");
    }

    public void parenShow1() {
        System.out.println("parent的parenShow1方法");
    }


}
