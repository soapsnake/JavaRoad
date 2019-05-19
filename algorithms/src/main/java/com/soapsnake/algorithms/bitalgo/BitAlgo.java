package com.soapsnake.algorithms.bitalgo;


import java.util.Arrays;

/**
 * 位运算
 */
public class BitAlgo {

    public static void main(String[] args) {

        System.out.println(Integer.toBinaryString(11) + ":" + 11);

        //右移1位相当于除以2, 11 >> 1 ==  11 / 2
        System.out.println(Integer.toBinaryString(11 >> 1) + " --> 11 >> 1 :" + (11 >> 1));

        //左移1位相当于乘以2的1次方(x2), 11 << 1 == 11 * 2
        System.out.println(Integer.toBinaryString(11 << 1) + "  --> 11 << 1 :" + (11 << 1));

        //左移2位相当于乘以2的2次方(x4)
        System.out.println("11 << 2 =" + (11 << 2));
        System.out.println("12 << 2 =" + (12 << 2));
        System.out.println("13 << 2 =" + (13 << 2));
        System.out.println("14 << 2 =" + (14 << 2));
        System.out.println("15 << 2 =" + (15 << 2));


        //左移3位相当于乘以2的3次方(x8)
        System.out.println("11 << 3 =" + (11 << 3));
        System.out.println("12 << 3 =" + (12 << 3));
        System.out.println("13 << 3 =" + (13 << 3));
        System.out.println("14 << 3 =" + (14 << 3));
        System.out.println("15 << 3 =" + (15 << 3));


        System.out.println("1 | 2 = " + (1 | 2));


        System.out.println("0xfffff = " + 0xfffff);


        //无符号右移1位相当于除以2, 11 >>>1 == 11 / 2 //这个运算符一般情况下无意义,也很少会用到
        System.out.println(Integer.toBinaryString(11 >>> 1) + ":" + (11 >>> 1));

        //不存在无符号左移位,以下算式报错
//        System.out.println(Integer.toBinaryString(11 <<< 1) + ":" + (11 <<< 1));
        int[] arr = {2,3,4};
        changeArr(arr);
        System.out.println(Arrays.toString(arr));

    }

     static void changeArr(final int[] arr) {
        arr[0] = 1;
    }

}
