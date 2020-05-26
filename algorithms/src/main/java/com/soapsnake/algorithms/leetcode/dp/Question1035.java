package com.soapsnake.algorithms.leetcode.dp;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-26
 */
public class Question1035 {

    //用线连A和B中相等的数字,求最多可以画多少条不相交的线
    //if A[i] and B[j] are same, increment count and advance i and j and work on the remaning arrays.
    //    Otherwise, we advance i and check the count on the remaining arrays,
    //   in another case we advance j and check, choose the max from the two sub-problems.
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];   //1.dp数组定义:dp[i][j] = 连线A中的前i个元素和B中的前j个元素的最大不相交线数

        //2.这里隐含了dp的初始化条件dp[0][0] = 0,为什么是0了?无法确定A[0]和B[0]一定是相等的吗?

        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                //3. dp递推公式
                if (A[i - 1] == B[j - 1]) {
                    //如果两数相等,那么如下递推公式成立
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    //如果两数不相等,那么dp[i][j]等于之前较大的一个数
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
        return dp[m][n];
    }
}
