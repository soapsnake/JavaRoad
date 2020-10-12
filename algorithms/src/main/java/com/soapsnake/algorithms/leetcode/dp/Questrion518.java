package com.soapsnake.algorithms.leetcode.dp;

/**
 *
 * Created on 2020-04-01
 */
public class Questrion518 {

    //leetcode518
    public int change(int amount, int[] coins) {
        //1.定义dp[i][j] = 在使用i种面额硬币达成总额j时所有的组合数量
        int[][] dp = new int[coins.length+1][amount+1];

        //2.初始条件 => 使用0种硬币达成总额0时所有的组合数量为1种
        dp[0][0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                //3. 转移方程:
                //使用i种面额硬币达到总额j的组合数量 =
                //使用(i-1)种面额硬币达到总额j的组合数量 + 使用i种面额硬币达到总额为(j - 第i个硬币的面额).....
                dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);
            }
        }
        //结果 = 使用所有种类的硬币达到amount总额的组合数量
        return dp[coins.length][amount];
    }
}
