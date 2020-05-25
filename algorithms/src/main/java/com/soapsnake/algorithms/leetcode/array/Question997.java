package com.soapsnake.algorithms.leetcode.array;

/**
 *
 * Created on 2020-05-10
 */
public class Question997 {

    //leetcode997
    public int findJudge(int N, int[][] trust) {
        int[] count = new int[N+1];
        for (int[] t: trust) {
            count[t[0]]--;
            count[t[1]]++;
        }
        for (int i = 1; i <= N; ++i) {
            if (count[i] == N - 1) return i;
        }
        return -1;
    }
}
