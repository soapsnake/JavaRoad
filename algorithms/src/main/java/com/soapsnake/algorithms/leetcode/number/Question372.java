package com.soapsnake.algorithms.leetcode.number;

/**
 *
 * Created on 2020-02-06
 */
public class Question372 {

    //leetcode372
    public int superPow(int a, int[] b) {
        if (a % 1337 == 0) return 0;
        int p = 0;
        for (int i : b) p = (p * 10 + i) % 1140;
        if (p == 0) p += 1440;
        return power(a, p, 1337);
    }
    public int power(int a, int n, int mod) {
        a %= mod;
        int ret = 1;
        while (n != 0) {
            if ((n & 1) != 0) ret = ret * a % mod;
            a = a * a % mod;
            n >>= 1;
        }
        return ret;
    }

    public static void main(String[] args) {
        Question372 question372 = new Question372();
        int[] b = {1, 0};
        System.out.println(question372.superPow(2, b));
    }
}
