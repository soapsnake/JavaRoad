package com.ld.thinkinjava.array;

/**
 * Created by liudun on 2017/6/9.
 */
public class ArrayTest {

    public static void main(String[] args) {

        Integer i1 = new Integer(10);

        Integer i2 = new Integer(30);

        Integer i3 = new Integer(40);

        Integer[] arr = {i1,i2,i3};

        for (Integer integer : arr){
            System.out.println(integer);
        }

    }
}
