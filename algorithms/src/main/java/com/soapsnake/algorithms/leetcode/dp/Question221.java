package com.soapsnake.algorithms.leetcode.dp;

public class Question221 {

    public static void main(String[] args) {
        Question221 question221 = new Question221();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}
        };
        System.out.println(question221.maximalSquare(matrix));
    }

    /**
     * Example:
     * <p>
     * Input:
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     * <p>
     * Output: 4
     * <p>
     * Given a 2D binary matrix filled with 0's and 1's,
     * find the largest square containing only 1's and return its area.
     */
    public int maximalSquare(char[][] matrix) {
        //这个解法不知道问题在哪里????
        if (matrix == null) {
            return 0;
        }
        if (matrix.length == 0) {
            return 0;
        }
        int len = matrix.length;
        int w = matrix[0].length;
        int totalmax = -1;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] == '1') {
//                    dfs(i, j, matrix, len, w, i, j);
                    int x = i, y = j;
                    int xlen = 0, ylen = 0;
                    while (x < len && matrix[x][j] == '1') {
                        x++;
                    }
                    xlen = x - i;
                    while (y < w && matrix[i][y] == '1') {
                        y++;
                    }
                    ylen = y - j;
                    int maxlen = Math.min(xlen, ylen);
                    totalmax = Math.max(totalmax, maxlen);
                }
            }
        }
        return totalmax;
    }

    //动态规划,这个也太他妈难懂了
    //  https://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
    public int maximalSquare2(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int max = 0;
        int[][] dp = new int[m + 1][n + 1];

        // dp(i, j) represents the length of the square whose lower-right corner is located at (i, j)
        //dp[i, j] 的值等于已(i,j坐标为左上顶点的最大正方形的面积的边长)
        // dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {

                    //这个dp[i][j]的赋值实在太他妈精髓了,谁能想得到!!!
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j - 1]), dp[i - 1][j]) + 1;
                    max = Math.max(dp[i][j], max); // update result
                }
            }
        }
        return max * max;
    }

    private void dfs(int xstart, int ystart, char[][] matrix, int len, int w, int x, int y) {
        int max = 0;
        System.out.println("xstart = " + xstart + " x = " + x);
        if (x >= len || y >= w || matrix[x][y] == '0') {
            int zenx = x - xstart;
            int zeny = y - ystart;
            max = Math.max(max, Math.min(zenx, zeny));
            System.out.println("max = " + max);
            return;
        }
        if (y + 1 < len) {
            dfs(xstart, ystart, matrix, len, w, x, y + 1);  //只往右走
        } else if (x + 1 < w) {
            dfs(xstart, ystart, matrix, len, w, x + 1, y);  //只往下走
        }
    }
}
