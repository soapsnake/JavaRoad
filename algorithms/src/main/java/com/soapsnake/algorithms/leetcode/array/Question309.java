package com.soapsnake.algorithms.leetcode.array;

/**
 *
 * Created on 2020-05-26
 */
public class Question309 {

    /**
     * 假设你有一个数组，其中第i个元素是某只股票在第i天的价格。
     * 设计一个算法来寻找最大利润。你可以完成任意数量的交易（即多次买入并卖出一股股票），但有以下限制。
     * 你不能同时进行多次交易（即必须在再次买入之前卖出股票）。
     * 卖出股票后，第二天不能再买入股票。(即冷却时间为1天)
     * Example:
     * Input: [1,2,3,0,2]
     * Output: 3
     * Explanation: transactions = [buy, sell, cooldown, buy, sell]
     */
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75931/Easiest-JAVA-solution-with-explanations
    //leetcode309
    //selling stock with cool down
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        /**
         * Let b2, b1, b0 represent buy[i - 2], buy[i - 1], buy[i]
         * Let s2, s1, s0 represent sell[i - 2], sell[i - 1], sell[i]
         */
        int b0 = -prices[0], b1 = b0;
        int s0 = 0, s1 = 0, s2 = 0;

        for (int i = 1; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b1 = b0;
            s2 = s1;
            s1 = s0;
        }
        return s0;
    }



}