package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/11/26 20:19
 */
public class Question122 {

    /**
     * Input: [7,1,5,3,6,4]
     * Output: 7
     * <p>
     * 不限制交易的笔数
     */
    public int maxProfit(int[] prices) {
        int total = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            /**
             *  最简单的一种情况:不限制买入和卖出的笔数,那么遍历数组,只要第二天比前一天的价格高,
             *  那我就前一天买入,第二天卖出,这样把能赚的钱都赚到,获益就会最大
             */
            if (prices[i + 1] > prices[i]) total += prices[i + 1] - prices[i];
        }
        return total;
    }

    /**
     * 限制交易笔数为1笔
     */
    public int maxProfit2(int[] prices) {
        int[] sell = new int[prices.length + 1];  //第i天为卖出状态的最大收益
        int[] buy = new int[prices.length + 1];  //第i天为买入状态的最大收益

        sell[0] = 0; //第一天不可能卖,这里的话还是
        buy[0] = 0;  //第一天为买入的话,由于不能卖,所以也不会有收益
        for (int i = 1; i < prices.length; i++) {
            //第i天为卖出状态的最大收益 = max(i-1天就已经卖出i天啥也不干, i-1天为买入但是第i天卖出)
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            //第i天为买入状态的最大收益 = max(i-1天就已经买入i天啥也不干, i-1天为卖出但是第i天买入)
//            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);  //在k=1的情况下,这条状态转移方程是错的

            //如果是限制一笔交易的情况下, sell[i - 1] - prices[i] 事实上是不用考虑的,因为卖的行为已经发生,代表所有机会已经用完
            buy[i] = Math.max(buy[i - 1], -prices[i]); //-prices[i]一定是负的,所以这里的max其实可以省略
        }

        //结果为什么取sell而不是buy的,因为股票一定要卖出状态才能获得收益,buy的话有可能到最后一天了还没卖出
        return sell[prices.length];
    }

    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length + 1][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + (prices[i] - fee));
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length][0];
    }
}