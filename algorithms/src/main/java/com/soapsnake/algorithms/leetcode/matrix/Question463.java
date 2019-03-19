package com.soapsnake.algorithms.leetcode.matrix;

/**
 * @author soapsnake
 * @date 2018/11/2
 */
class Question463 {

    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (i - 1 < 0 || grid[i - 1][j] != 1) { //左侧无
                        res += 1;
                    }
                    if (i + 1 == grid.length || grid[i + 1][j] != 1) { //右侧无
                        res += 1;
                    }
                    if (j - 1 < 0 || grid[i][j - 1] != 1) { //下方
                        res += 1;
                    }
                    if (j + 1 == grid[i].length || grid[i][j + 1] != 1) { //上方
                        res += 1;
                    }
                }
            }
        }
        return res;
    }
}
