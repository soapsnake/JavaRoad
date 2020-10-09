package com.soapsnake.algorithms.leetcode.dp;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-07
 */
public class Question188 {

    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) {
            return quickSolve(prices);
        }

        //dp数组:k笔交易的第i天卖出的最大收益
        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            //tmpMax means the maximum profit of just doing at most i-1 transactions,
            //using at most first j-1 prices, and buying the stock at price[j] - this is used for the next loop.
            int tmpMax = -prices[0];  //每一笔交易的这个tmpMax的最大收益初始值都是相同的
            for (int j = 1; j < len; j++) {
                //dp[i][j - 1]的含义是第j天不卖出,所以第j天的收益和j-1天是相同的
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + tmpMax);
                tmpMax = Math.max(tmpMax, dp[i - 1][j - 1] - prices[j]);
            }
        }
        return dp[k][len - 1];
    }

    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++) {
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
