package com.soapsnake.algorithms.leetcode.dp;

/**
 * 
 * Created on 2020-08-16
 */
public class Question123 {

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * <p>
     * 在截止之前,你可以有最多两笔交易(买入-卖出最多两次),totalK = 2
     */
    //leetcode123,最多允许两笔买卖,求最大收益
    public int maxProfit(int[] prices) {
        int buyOne = Integer.MAX_VALUE;
        int ProfitOne = 0;   //第一笔买卖的收益
        int buyTwo = Integer.MAX_VALUE;
        int ProfitTwo = 0;   //第二笔买卖的收益,求得就是这个
        for(int price : prices) {
            buyOne = Math.min(buyOne, price);  // Assume we only have 0 money at first
            ProfitOne = Math.max(ProfitOne, price - buyOne);

            //第二笔买卖的买入价格(成本)应该扣除第一笔买卖赚的钱,要从全局考虑问题撒
            buyTwo = Math.min(buyTwo, price - ProfitOne);     // If I made $100 profit in 1st trans'. Then the 2nd Stock(maybe $300) I buy is actually $300 - $100 = $200 for me.
            ProfitTwo = Math.max(ProfitTwo, price - buyTwo);   // And finally The maximum profit I make with the 2nd trans' is my actuall profit with 2 transaction.
        }
        return ProfitTwo;
    }

}
