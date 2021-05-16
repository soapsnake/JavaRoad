package com.soapsnake.algorithms.leetcode.dp;

/**
 * 
 * Created on 2021-03-17
 */
public class Question714 {

    /**
     * Given any day I, its max profit status boils down to one of the two status below:
     * (1) buy status:
     * buy[i] represents the max profit at day i in buy status, given that the last action you took is a buy action at day K, where K<=i. And you have the right to sell at day i+1, or do nothing.
     * (2) sell status:
     * sell[i] represents the max profit at day i in sell status, given that the last action you took is a sell action at day K, where K<=i. And you have the right to buy at day i+1, or do nothing.
     *
     * Let's walk through from base case.
     *
     * Base case:
     * We can start from buy status, which means we buy stock at day 0.
     * buy[0]=-prices[0];
     * Or we can start from sell status, which means we sell stock at day 0.
     * Given that we don't have any stock at hand in day 0, we set sell status to be 0.
     * sell[0]=0;
     *
     * Status transformation:
     * At day i, we may buy stock (from previous sell status) or do nothing (from previous buy status):
     * buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
     * Or
     * At day i, we may sell stock (from previous buy status) or keep holding (from previous sell status):
     * sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
     *
     * Finally:
     * We will return sell[last_day] as our result, which represents the max profit at the last day, given that you took sell action at any day before the last day.
     * We can apply transaction fee at either buy status or sell status.
     * So here come our two solutions:
     *
     * Solution I -- pay the fee when buying the stock:
     * public int maxProfit(int[] prices, int fee) {
     *         if (prices.length <= 1) return 0;
     *         int days = prices.length, buy[] = new int[days], sell[] = new int[days];
     *         buy[0]=-prices[0]-fee;
     *         for (int i = 1; i<days; i++) {
     *             buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i] - fee); // keep the same as day i-1, or buy from sell status at day i-1
     *             sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]); // keep the same as day i-1, or sell from buy status at day i-1
     *         }
     *         return sell[days - 1];
     *     }
     *
     * Solution II -- pay the fee when selling the stock:
     *     public int maxProfit(int[] prices, int fee) {
     *         if (prices.length <= 1) return 0;
     *         int days = prices.length, buy[] = new int[days], sell[] = new int[days];
     *         buy[0]=-prices[0];
     *         for (int i = 1; i<days; i++) {
     *             buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]); // keep the same as day i-1, or buy from sell status at day i-1
     *             sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee); // keep the same as day i-1, or sell from buy status at day i-1
     *         }
     *         return sell[days - 1];
     *     }
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1) return 0;
        int days = prices.length;
        int[] buy = new int[days];  //如果第i天买入将会获得的最大收益
        int[] sell = new int[days];  //如果第i天卖出将会获得的的最大收益
        buy[0] = -prices[0] - fee;   //初始条件: 如果第一天卖出的最大收益 = (负)第一天买入成本 - 交易费
        for (int i = 1; i<days; i++) {
            //第i天买入的最大收益 = max(i-1天买入但是i天什么都不做(i天不买入),  i-1天卖出股票获得收益 - 第i天买入 - 买入的交易费)
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i] - fee); // keep the same as day i-1, or buy from sell status at day i-1
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]); // keep the same as day i-1, or sell from buy status at day i-1
        }
        return sell[days - 1];
    }
}
