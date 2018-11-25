package com.soapsnake.thinkinjava.array;

import java.util.Random;

/**
 * Created by soapsnake on 2017/6/10.
 */
public class ArrayTest2 {

    static Random rand = new Random();

    static int pRand(int mod) {
        return Math.abs(rand.nextInt()) % mod + 1;
    }

    public static void main(String[] args) {

        Integer[] a = new Integer[pRand(20)];

        prt("length of a = " + a.length);

        for (int i = 0; i < a.length; i++) {

            a[i] = new Integer(pRand(500));

            prt("a[" + i + "] = " + a[i]);

        }
    }

    static void prt(String s) {
        System.out.println(s);
    }


    //public > protected > none > private
    //protected还有从其他dir通过继承的方式实现访问的可能,none在其他dir完全没有访问的可能性
    void testPack() {
        System.out.println("can access this method only this dir!");
    }

    protected void testProtec() {
        System.out.println("can access from other dir");
    }

} 


