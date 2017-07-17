package com.ld.thinkinjava.generics;

/**
 * Created by liudun on 2017/7/14.
 */
public class TestClass<T extends Number> {


    public static void main(String[] args) {

        //容器定界,容器有了界限后就不能再随意的创建了
//        TestClass<String> testClass = new TestClass<String>();   //string不是number的子类

        TestClass<Integer> testClass1 = new TestClass<>();
    }

}
