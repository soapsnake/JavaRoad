package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/11/26 20:19
 */
public class Question122 {

    /**
     * Input: [7,1,5,3,6,4]
     * Output: 7
     */
    public int maxProfit(int[] prices) {
        //1. 一次买入必须有一次卖出,所以周6不能买入,周一不能卖出
        //买入为-, 卖出为+


        //如果题目不限制同一天即能买入也能卖出,那么用下面的解法就可以了
        int total = 0;
        for (int i=0; i< prices.length-1; i++) {
            if (prices[i+1]>prices[i]) total += prices[i+1]-prices[i];
        }

        return total;


        //todo 如果限制一天只能买入/或者卖出了?如何用DP解决这个问题?


    }
}
