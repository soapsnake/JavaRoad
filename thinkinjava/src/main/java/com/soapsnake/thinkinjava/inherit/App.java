package com.soapsnake.thinkinjava.inherit;

/**
 * Created by soapsnake on 2017/7/27.
 */
public class App {

    public static void main(String[] args) {

        Parent parent = new Parent();


        Son son = new Son();


        parent.parent1 = "在mian里改了父parent的值";

        son.printpar();

        son.parent1 = "在main里改son的值";

        son.printpar();

        System.out.println(parent.parent1);

        //这个方法是父类的方法,子类没有覆盖
        son.parentShow();

        //这个方法子类,父类当中都有,子类进行了覆盖
        son.parenShow1();


    }

}
