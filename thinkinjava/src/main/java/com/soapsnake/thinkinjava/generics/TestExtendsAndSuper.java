package com.soapsnake.thinkinjava.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudun on 2017/7/14.
 */
public class TestExtendsAndSuper {

    public static void main(String[] args) {


        //生产者list,只能取出,不能放入
        List<? extends Number> list = new ArrayList<Integer>();
        //不能存入
//        list.add(2);
//        list.add(2L);
//        list.add(3.0);

        //但是能够取出
        //取出的元素不能是子类型
//        Integer integer = list.get(1);
        //只能是通配符的上界
        Number number = list.get(1);


        //消费者型泛型容器,只能够插入,不能取出(取出来的元素丢失所有类型信息)
        List<? super Integer> list1 = new ArrayList<>();

        list1.add(12);
        list1.add((int) 12);
//        list1.add((short)12);
//        list1.add((long)12);
//
//        Number number1 = list1.get(1);
//        Integer number2 = list1.get(1);

        Object number3 = list1.get(1);


    }
}
