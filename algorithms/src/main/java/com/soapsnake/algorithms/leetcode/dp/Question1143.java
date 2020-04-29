package com.soapsnake.algorithms.leetcode.dp;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-04-26
 */
public class Question1143 {

    //leetcode1143
    public int longestCommonSubsequence(String text1, String text2) {
        //最长公共子串
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length(); ++i)
            for (int j = 0; j < text2.length(); ++j)
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] =  Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        double a = 89.1231321;
        System.out.println(a);

        DecimalFormat format = new DecimalFormat("#0.00");
        System.out.println(format.format(a));

        double b = new BigDecimal(a).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(b);
    }
}
