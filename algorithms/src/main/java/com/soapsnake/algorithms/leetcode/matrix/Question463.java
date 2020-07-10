package com.soapsnake.algorithms.leetcode.matrix;

/**
 * @author soapsnake
 * @date 2018/11/2
 */
class Question463 {

    //leetcode463
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    //方块是岛屿的情况下
                    if (i == 0 || grid[i - 1][j] != 1) {
                        //如果是第一行的方块,或者上方是水的方块,那么上边可以计入
                        res += 1;
                    }
                    if (i + 1 == grid.length || grid[i + 1][j] != 1) {
                        //如果是最后一行的方块,或者下方是水的方块,那么下边可以计入
                        res += 1;
                    }
                    if (j == 0 || grid[i][j - 1] != 1) {
                        //如果是第一竖排的方块,或者左侧是水的方块,那么左边可以计入
                        res += 1;
                    }
                    if (j + 1 == grid[i].length || grid[i][j + 1] != 1) {
                        //如果是最右侧竖排的方块,或者右侧是水的方块,那么右边可以计入
                        res += 1;
                    }
                }
            }
        }
        return res;
    }
}
