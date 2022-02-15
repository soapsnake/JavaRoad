package com.soapsnake.algorithms.bitalgo;


import java.util.Arrays;
import java.util.Random;

import javax.sound.sampled.SourceDataLine;

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
        int[] arr = {2, 3, 4};
        changeArr(arr);
        System.out.println(Arrays.toString(arr));


        //异或XOR运算: ^ 运算用来判断两个数字是否相同,如果两个数字相同那么返回0, 如果不相同那么返回1
        int a = 1;
        int b = 1;
        System.out.println(" 1 ^ 1 = " + (a ^ b));
        
        b = 0;
        System.out.println(" 1 ^ 0 = "  + (a ^ b));
        a = 0;
        System.out.println(" 0 ^ 0 = "  + (a ^ b));
        b = 1;
        System.out.println(" 0 ^ 1 = "  + (a ^ b));
        
        int c = new Random().nextInt(100);
        System.out.println("任意一个数字异或自身总是为0, c ^ c  = "  + (c ^ c));

        System.out.println("任意一个数字异或0总是等于自身 c = " + c + ", c ^ 0  = "  + (c ^ 0));


    }

    static void changeArr(final int[] arr) {
        arr[0] = 1;
    }

}
