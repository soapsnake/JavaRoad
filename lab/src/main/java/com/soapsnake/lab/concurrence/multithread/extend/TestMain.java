package com.soapsnake.lab.concurrence.multithread.extend;

public class TestMain {

    public static void main(String[] args) {
        Parent parent = new Son();
        parent.function();            //多态,虽然parent是个父类型对象,但是执行的却是子类的方法
        parent.function0();            //如果子类没有对父类的方法进行覆盖,则执行的是父类的方法
        ((Son) parent).function1();      //向下转型

        Son son = new Son();
        son.function();
        son.function0();               //继承,子类中会有所有的父类方法
        son.function1();

        son.funcitionOfParent();


        //函数式接口必须只能有一个方法,否则不是函数式接口
        //函数式接口类型的对象,应该是一个函数              //不需要声明变量类型 ,当只有一个参数时,圆括号都可以省略
        SonOtherParent otherParent = number ->     //lambda只适用于单方法接口,即函数式接口
                System.out.println("lambda function");     //lambda表达式应该理解为一个函数,而不是一个方法
        //当函数内只有一句话时,花括号都可以省略
        otherParent.function2(3);   //

        SonOtherParent otherParent2 = new Son();
        otherParent2.function2(3);

    }
}
