package com.soapsnake.pattern.templatemethod;

/**
 * Created by soapsnake on 2017/8/24.
 */
public class Son1 extends Absparent {
    @Override
    public void overideMethod() {

        super.parentMethod();

        this.parentMethod();


        parentMethod();

        System.out.println("son1 oveeride method");
    }

////    @Override
//    public void parentMethod(){
//        System.out.println("son overide parent method");
//    }

}
