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
    //leetcode309
    //selling stock with cool down
    public int maxProfit(int[] prices) {
        //https://soulmachine.gitbooks.io/algorithm-essentials/content/java/dp/best-time-to-buy-and-sell-stock-with-cooldown.html
        if (prices == null || prices.length == 0) return 0;

        int[] sell = new int[prices.length]; //第i天状态是卖出以及卖出冷却时的最大收益(手中没有股票了)
        int[] buy = new int[prices.length];  //第i天状态是买入时的最大收益(手中还有股票)

        //为什么第0天状态为卖出时的最大收益是0了?因为第一天不允许卖出吗?
        sell[0] = 0;
        //如果第0天的状态是买入,那么最大收益是负的,因为没有卖出
        buy[0] = -prices[0];

        //sell[i]表示第i天未持股时，获得的最大利润
        //buy[i]表示第i天持有股票时，获得的最大利润
        for (int i = 1; i < prices.length; ++i) {
            //对于sell[i]，最大利润有两种可能，一是今天没动作跟昨天未持股状态一样，二是今天卖了股票
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);

            //对于buy[i]，最大利润有两种可能，一是今天没动作跟昨天持股状态一样，二是前天卖了股票，今天买了股票，
            //因为 cooldown 只能隔天交易，所以今天买股票要追溯到前天的状态
            buy[i] = Math.max(buy[i - 1], (i > 1 ? sell[i - 2] : 0) - prices[i]);
        }

        //最终我们要求的结果是sell[n - 1]，表示最后一天结束时，手里没有股票时的最大利润。
        return sell[prices.length - 1];
    }



}