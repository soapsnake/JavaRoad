package com.ld.pattern.templatemethod;

/**
 * Created by liudun on 2017/8/24.
 */
public abstract class Absparent {


    public Absparent() {
    }


    public final void parentMethod() {
        System.out.println("父类final方法");
    }

    public abstract void overideMethod();


}
