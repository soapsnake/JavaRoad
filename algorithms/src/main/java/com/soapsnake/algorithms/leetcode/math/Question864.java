package com.soapsnake.algorithms.leetcode.math;

import java.util.Arrays;

/**
 * 
 * Created on 2021-03-22
 */
public class Question864 {

    public boolean reorderedPowerOf2(int N) {
        long c = counter(N);
        for (int i = 0; i < 32; i++)
            if (counter(1 << i) == c) return true;
        return false;
    }

    public long counter(int N) {
        long res = 0;
        for (; N > 0; N /= 10) res += (int) Math.pow(10, N % 10);
        return res;
    }

    //leetcode864
    public static boolean reorderedPowerOf2V2(int N) {
        char[] a1 = String.valueOf(N).toCharArray();
        Arrays.sort(a1);
        String s1 = new String(a1);

        for (int i = 0; i < 31; i++) {
            char[] a2 = String.valueOf((int)(1 << i)).toCharArray();
            Arrays.sort(a2);
            String s2 = new String(a2);
            if (s1.equals(s2)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(reorderedPowerOf2V2(24));
    }
}
