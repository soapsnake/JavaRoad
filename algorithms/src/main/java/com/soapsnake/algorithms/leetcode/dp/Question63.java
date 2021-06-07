package com.soapsnake.algorithms.leetcode.dp;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-26 21:21
 */
public class Question63 {

    //leetcode63
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)   //如果j点是不可达的,那么改点记录的路径数为0
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];   //否则每个点的可达路径数就是之前所有点的可达数之和(青蛙跳楼梯,斐波拉西数列)
            }
        }
        return dp[width - 1];
    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid.length == 1) {
            return 1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    //该点可以走
                    obstacleGrid[i][j] = 1;
                } else {
                    //该点不可以走
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    //如果该点可达,那么到该点的路径只有两条: 1>从上面往下走一步,2>从左边往右走一步
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
            }
        }
        //obstacleGrid 直接充当了dp数组,其实和青蛙跳楼梯是同一类问题
        return obstacleGrid[m - 1][n - 1];
    }

}
