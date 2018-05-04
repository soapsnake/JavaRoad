package com.ld.thinkinjava.generics;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudun on 2017/7/14.
 */
public class TestClass<T extends Number> {

    void add(String parm){
        System.out.println(parm);
    }


    public static void main(String[] args) {

        //容器定界,容器有了界限后就不能再随意的创建了
//        TestClass<String> testClass = new TestClass<String>();   //string不是number的子类

        TestClass<Integer> testClass1 = new TestClass<>();


        TestClass<?> testClass2 = new TestClass<>();
        testClass2.add("dsada");

        List<?> list = new ArrayList<>();

        List<? extends Collection> list1 = new ArrayList<>();
        List<? super List> list2 = new ArrayList<>();

        List<String> list3 = new ArrayList<>();
        list3.add("dsadsa");
        list2.add(list3);
        list2.add(new LinkedList());
//        list2.add(new IdentityLinkedList());

        System.out.println(list2);
    }

}
