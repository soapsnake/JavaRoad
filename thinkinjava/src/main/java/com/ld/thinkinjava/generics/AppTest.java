package com.ld.thinkinjava.generics;

import com.ld.thinkinjava.classtest.Son;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by liudun on 2017/7/8.
 */
public class AppTest {

    public static void main(String[] args) {
        GenericsTest test = new GenericsTest();

        test.testGen(1);

        test.testGen("fuck");


        /**
         *        泛型方法的意义:
         *        1.入参可以是任何类型,但是返回值要吗是指定类型,要吗和入参的类型一致
         *        2.返回值前的<T>表明了这是一个泛型方法,入参的类型必须指定为T,不指定的话,相当于没有使用泛型
         *        3.泛型方法的返回值的类型和T没有一毛钱的关系,如果你想让方法也返回T类型,那就<T> T m(T arg){}
         *        4.如果想要泛型返回String,那就<T> String m(T arg){}
         *        5.总而言之,想要泛型就在返回值类型前加<T>,这个T管控了入参和返回值的类型
         *        6.假如你想泛参数,那就入参类型写成T
         *        7.假如你想泛返回值,那就返回值的类型写成T
         *        8.假如你的入参返回值都想泛,那就写成<T> T (T t){}

         */

        test.testGen(new Date());

        System.out.println(test.get(new Date()));


        //手动指定泛型方法泛的类型,这种方式会破坏掉泛型方法的意义,但是可以用来解决某些问题,见thinking in java P363
        test.<Date>get(new Date());

        //超级泛型方法:①入参个数不固定,②入参类型不固定,③每个入参的类型不固定
        test.get6("string",1,new Date());

        //newObj专门用来传入类型而生成这个类型的参数
        test.newObj(String.class);


        SomeClass<Son> someClass = new SomeClass<>(new Son());

        someClass.dealWithT();

        List<Number> list = new ArrayList<>();
        Integer i1 = 123;
        Long l2 = 1234L;
        list.add(i1);
        list.add(l2);

        SomeClass<Integer> someInte = new SomeClass<>();
        SomeClass<Number> someNum = new SomeClass<>();
        SomeClass some3 = new SomeClass();
        //虽然Number是Integer的父类型
        // 但是SomeClass<Number> 类型和SomeClass<Integer> 之间没有任何联系
//        test(someInte);

        test(someNum);
        test(some3);

        List<X> xList = Arrays.asList(new X());

        System.out.println(">>>>>>>>"+xList.getClass().getSimpleName() + xList.size());

    }


    public static  void test(SomeClass<Number> some){

    }













}
