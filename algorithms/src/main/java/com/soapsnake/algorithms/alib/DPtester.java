package com.soapsnake.algorithms.alib;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;

public class DPtester {


    /**
     * 题目：一个台阶总共有n级，如果一次可以跳1级，也可以跳2级。
     * 求总共有多少总跳法，并分析算法的时间复杂度。
     */
    public int countJump(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return countJump(n - 1) + countJump(n - 2);
    }

    public int countJump2(int n) {
        if (n == 0) {
            return 0;
        }
        int one = 1;
        int second = 2;
        int cur = 0;
        while (n-- > 2) {
            cur = one + second;
            one = second;
            second = cur;
        }
        return cur;
    }

    @Test
    public void testCountJump() {
        int n = 45;
//        System.out.println(countJump(n));
        System.out.println(countJump2(n));
    }

    //二维数组最大路径和
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];  //1.dp[i][j]指到ij位置的最大路径和
        dp[0][0] = dungeon[0][0];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + dungeon[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }
}
