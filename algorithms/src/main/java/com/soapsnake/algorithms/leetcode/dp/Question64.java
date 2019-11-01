package com.soapsnake.algorithms.leetcode.dp;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-27 22:25
 */
public class Question64 {

    int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Question64 question64 = new Question64();
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(question64.minPathSum(grid));
    }

    /**
     * 树的递归,解法是对的,但是复杂度太高了
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {

        if (grid == null) {
            return 0;
        }
        dfs(grid, 0, 0, 0);
        return min;
    }

    private void dfs(int[][] grid, int right, int down, int temp) {
        if (right == grid.length - 1 && down == grid[0].length - 1) {
            temp += grid[right][down];
            min = Math.min(temp, min);
            return;
        }
        temp = temp + grid[right][down];
        if (right < grid.length - 1) {
            dfs(grid, right + 1, down, temp);
        }
        if (down < grid[0].length - 1) {
            dfs(grid, right, down + 1, temp);
        }
    }

    /**
     * dp 解法
     *
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {   //第一横行,只可能向右移动
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {  //第一纵列,只可能向下移动
                    grid[i][j] += grid[i - 1][j];
                } else {       //动态规划思想:如果每次都取两种走法中路径短的那种,那么最后的和就一定是最短的
                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        return grid[m - 1][n - 1]; //计算完后grid数组每一格中数字的值都是从起点到该点的最短路径和
    }


}
