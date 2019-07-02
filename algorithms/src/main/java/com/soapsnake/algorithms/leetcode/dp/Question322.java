package com.soapsnake.algorithms.leetcode.dp;

import java.util.Arrays;

public class Question322 {

    /**
     * 硬币面值: 1, 2, 5
     * n = f(amount)    //n是想达到amount数量的钱需要的硬币数据量
     * 以11为例:
     * f(0) = 0                                             //0元需要的硬币数量为0
     * f(1) = 1                                             //1元需要的硬币数量为1
     * f(2) = min((1 + f(1)), 1) = min(2, 1) = 1            //2元需要的硬币有两种策略:两个1元,或者1个2元,取数量小的
     * f(3) = min((1 + f(2)), 1 + 1 + 1) = min(2, 3) = 2    //3元 = 3个1元或者1个1,1个2
     * f(4) = min((1 + f(3)), (f(2) + f(2))) = min(3, 2)= 2
     * f(5) = 1 + f(4) 或者 1 +
     *.........
     * f(n) = min((1 + f(n - (不同面额)))  //如果面额池有1,2,5三个值,这里就要分别算三次,即f(n-1),f(n-2),f(n-5)
     */
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {  //有多少种不同面额这里就要算多少次
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        Question322 question322 = new Question322();
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(question322.coinChange(coins, amount));
    }
}
