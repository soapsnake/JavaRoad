package com.soapsnake.algorithms.leetcode.biner;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-10 00:14
 */
public class Question192 {

    //智障版解法
    public int hammingWeight(int n) {
        if (n == 0) {
            return 0;
        }
        String biner = Integer.toBinaryString(n);
        int res = 0;
        for (int i = 0; i < biner.length(); i++) {
            if (biner.charAt(i) == '1') {
                res++;
            }
        }
        return res;
    }


    //大神版解法
    public static int hammingWeight2(int n) {
        int ones = 0;
        while(n!=0) {
            ones = ones + (n & 1);
            n = n>>>1;
        }
        return ones;
    }
}
