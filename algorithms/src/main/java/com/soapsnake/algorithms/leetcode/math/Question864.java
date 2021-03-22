package com.soapsnake.algorithms.leetcode.math;

/**
 * @author liudun <liudun@kuaishou.com>
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
}
