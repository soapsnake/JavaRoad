package com.soapsnake.algorithms.leetcode.dp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 */
class Question70 {

    //解法1:斐波那契数列的递归算法,利用map缓存中间结果(字典缓存算法)
    Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Question70 question70 = new Question70();
        long start1 = System.nanoTime();
        System.out.println(question70.climbStairs(40));

        System.out.println("climbStairs用时: " + (System.nanoTime() - start1));

        long start2 = System.nanoTime();
        System.out.println(question70.climbStairs2(40));
        System.out.println("climbStairs2用时: " + (System.nanoTime() - start2));

    }

    public int climbStairs(int n) {
        Integer temp = map.get(n);
        if (temp != null) {
            return temp;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int res = climbStairs(n - 1) + climbStairs(n - 2);
        map.put(n, res);
        return res;
    }

    //自底向上计算斐波拉契数列,时间复杂度再少一个数量级,而且空间复杂度为0
    public int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int fib1 = 1;
        int fib2 = 2;
        int fibn = 0;
        for (int i = 3; i <= n; i++) {
            fibn = fib1 + fib2;
            fib1 = fib2;
            fib2 = fibn;
        }
        return fibn;
    }

    public int rob(int[] nums) {
        int robPrev = 0;
        int notRobPrev = 0;

        for (int i = 0; i < nums.length; i++) {
            int robCurrent = notRobPrev + nums[i];
            int notRobCurrent = Math.max(robPrev, notRobPrev);

            robPrev = robCurrent;
            notRobPrev = notRobCurrent;
        }
        return Math.max(robPrev, notRobPrev);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
