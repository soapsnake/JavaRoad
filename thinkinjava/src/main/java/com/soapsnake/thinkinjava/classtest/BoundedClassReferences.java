package com.soapsnake.thinkinjava.classtest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soapsnake on 2017/7/4.
 */
public class BoundedClassReferences<T> {

    public List<T> list = new ArrayList<>();

    public static void main(String[] args) {

        //bounde满足了某种类型限制:必须是Number类的子类型
        Class<? extends Number> bounded = int.class;

        bounded = double.class;
        bounded = Number.class;

        //这句报错了,因为string类型并不是number的子类型
//        bounded = String.class;

//        list.add("string");
    }


}
