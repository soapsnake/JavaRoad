package com.soapsnake.algorithms.leetcode.biner;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-28
 */
public class Question338 {

    //leetcode338
    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i & 1);
        return f;
    }
}
