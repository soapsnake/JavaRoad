package com.soapsnake.algorithms.bit;


/**
 * 位运算
 */
public class BitAlgo {

    public static void main(String[] args) {

        System.out.println(Integer.toBinaryString(11) + ":" + 11);

        //右移1位相当于除以2, 11 >> 1 ==  11 / 2
        System.out.println(Integer.toBinaryString(11 >> 1) + " --> 11 >> 1 :" + (11 >> 1));

        //左移1位相当于乘以2, 11 << 1 == 11 * 2
        System.out.println(Integer.toBinaryString(11 << 1) + "  --> 11 << 1 :" + (11 << 1));

        //无符号右移1位相当于除以2, 11 >>>1 == 11 / 2 //这个运算符一般情况下无意义,也很少会用到
        System.out.println(Integer.toBinaryString(11 >>> 1) + ":" + (11 >>> 1));

        //不存在无符号左移位,以下算式报错
//        System.out.println(Integer.toBinaryString(11 <<< 1) + ":" + (11 <<< 1));

    }

}
