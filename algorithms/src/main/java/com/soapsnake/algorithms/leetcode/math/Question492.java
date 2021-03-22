package com.soapsnake.algorithms.leetcode.math;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-28 00:11
 */
public class Question492 {

    public static void main(String[] args) {
        Question492 question492 = new Question492();
        int area = 2;
        System.out.println(Arrays.toString(question492.constructRectangle(area)));
    }

    /**
     * 1.  如果a能整除b,那么 a % b == 0
     * 2. Math.squrt()可以用来开方
     */
    public int[] constructRectangle(int area) {
        int[] res = new int[2];   //l >= w
        int diffMin = Integer.MAX_VALUE;

        for (int i = 1; i <= area; i++) {
            //能整除
            if (area % i == 0) {
                int t1 = i;
                int t2 = area / i;
                if (t1 >= t2) {
                    if (Math.abs(t1 - t2) < diffMin) {
                        res[0] = t1;
                        res[1] = t2;
                        diffMin = Math.abs(t1 - t2);
                    }
                }
            }
        }
        return res;
    }
}
