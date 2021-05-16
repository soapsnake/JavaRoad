package com.soapsnake.algorithms.leetcode.dp;

/**
 * 
 * Created on 2021-03-27
 */
public class Kmp {

    private int[][] dp;
    private String pattern;
    private int M;

    public Kmp(String pattern) {
        this.dp = generateNext(pattern);
        this.pattern = pattern;
        this.M = pattern.length();
    }

    private int[][] generateNext(String pattern) {
        int[][] dp = new int[pattern.length()][256];  //ascii码的数量一共是256个

        dp[0][pattern.charAt(0)] = 1;

        int X = 0;

        for (int i = 1; i < pattern.length(); i++) {
            for (int j = 0; j < 256; j++) {
                if (pattern.charAt(i) == j) {
                    dp[i][j] = i + 1;
                } else {
                    dp[i][j] = dp[X][j];
                }
            }
            X = dp[X][pattern.charAt(i)];
        }
        return dp;
        
    }

    public int search(String txt) {
        int N = txt.length();
        int i = 0;
        for (int j = 0; j < N; j++) {
            i = dp[i][txt.charAt(j)];
            if (i == M) {
                return j - M + 1;
            }
        }
        return -1;
    }

}
