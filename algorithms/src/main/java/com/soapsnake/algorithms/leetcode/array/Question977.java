package com.soapsnake.algorithms.leetcode.array;

import java.net.DatagramPacket;
import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-02 21:47
 */
public class Question977 {

    public int[] sortedSquares(int[] nums) {
        //思路,把所有的负数全变成正的(反正要平方的),然后重新排序,最后求平方(排序整数平方后仍然是有序的)
        //复杂度nlog(n),取决于排序
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
            } else {
                break;
            }
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i] * nums[i];
        }
        return res;
    }

    public int[] sortedSquares2(int[] nums) {
        int[] res = new int[nums.length];
        int l = 0, r = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (Math.abs(nums[l]) >= Math.abs(nums[r])) {
                res[i] = nums[l] * nums[l];
                l++;
            } else {
                res[i] = nums[r] * nums[r];
                r--;
            }
        }
        return res;
    }


    public int maxSubarraySumCircular(int[] nums) {
        int finalMax = Integer.MIN_VALUE;
        int finalMin = Integer.MAX_VALUE;
        int curTotalMax = 0;
        int curTotalMin = 0;
        int total = 0;

        for (int i = 0; i < nums.length; i++) {
            curTotalMax = Math.max(nums[i], curTotalMax + nums[i]);
            finalMax = Math.max(curTotalMax, finalMax);

            curTotalMin = Math.min(nums[i], curTotalMin + nums[i]);
            finalMin = Math.min(curTotalMin, finalMin);

            total += nums[i];
        }

        if (finalMax > 0) {
            return Math.max(finalMax, total - finalMin);
        }
        return finalMax;
    }


    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length + 1][2];

        //第0天不持有
        dp[0][0] = 0;
        //第0天持有
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            //第i天不持有: ①i-1天同样不持有 ②i-1天持有但是i天卖出
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + (prices[i] - fee));

            //第i天持有: ①i-1天不持有i天买入 ②i - 1天就持有
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[prices.length][0];
    }

}
