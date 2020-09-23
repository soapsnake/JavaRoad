package com.soapsnake.algorithms.leetcode.dp;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-08-16
 */
public class Question123 {

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * <p>
     * 在截止之前,你可以有最多两笔交易(买入-卖出最多两次),totalK = 2
     */
    //leetcode123
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int totalK = 2;

        //dp[1][...] 代表第一比交易的最大收益可能
        //dp[2][...] 代表第二比交易的最大收益可能
        //...
        //dp[k][...] 代表第k比交易的最大收益可能
        //由于两笔交易完全没有关联,所以可以直接算
        int[][] dp = new int[totalK + 1][prices.length];
        for (int k = 1; k <= totalK; k++) {//profit = 0 when k = 0
            for (int i = 1; i < prices.length; i++) {
                //第i天的最大收益为第i天的价格减去第一天的价格(第一天买入算支出,第i天的卖出是收入,利润=收入-支出)(如果为负那就取0)
                int maxProfitSellOnDayI = Math.max(0, prices[i] - prices[0]);//buy on day 0, sell on day i
                for (int j = 1; j < i; j++) {//buy on day j, sell on day i

                    //第k天的收益,有两种算法:第k天如果不卖,那么第k天的收益就和k-1天相同,
                    //maxProfitSellOnDayI 就代表第k天不卖,所以第k天的收益与k-1天的收益相同
                    //dp[k - 1][j - 1] + prices[i] - prices[j]代表第k天卖出
                    maxProfitSellOnDayI = Math.max(maxProfitSellOnDayI, dp[k - 1][j - 1] + prices[i] - prices[j]);
                }

                //这里Math.max的含义:如果第i天不卖,那么第i天的收益和i-1天相同,所以相当于比较卖和不卖哪种收益最大
                dp[k][i] = Math.max(dp[k][i - 1], maxProfitSellOnDayI);//sell on day i OR not
            }
        }
        return dp[totalK][prices.length - 1];
    }

    //Input: [7,1,5,3,6,4]
    //Output: 5
    //Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    //             Not 7-1 = 6, as selling price needs to be larger than buying price.
    //只允许有一笔交易,要求最大利润
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] dp = new int[prices.length];
        dp[0] = prices[0];  //初始化

        //dp[i]与dp[i+1]的关系:如果i+1天不卖,那么i+1和第i天的收益相同,如果卖出,取二者的max
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(prices[i] - prices[i-1] + dp[i-1], 0);
        }
        return dp[prices.length - 1];
    }

    //正确解
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int maxProfit = 0, prevProfit = 0;
        for(int i=1;i<prices.length;i++) {
            //第i天的最大收益 = (i天卖出 - i-1天买入的收益) + 第i-1天的收益
            int profit = Math.max(prices[i] - prices[i-1] + prevProfit, 0);

            //maxProfit是全局最大收益
            maxProfit = Math.max(profit, maxProfit);
            prevProfit = profit;
        }
        return maxProfit;
    }
}
