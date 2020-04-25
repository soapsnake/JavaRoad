package com.soapsnake.algorithms.leetcode.matrix;

public class Question200 {

    /**
     * Example 1:
     * Input:
     * 11110
     * 11010
     * 11000
     * 00000
     * Output: 1
     * <p>
     * Example 2:
     * Input:
     * 11000
     * 11000
     * 00100
     * 00011
     * Output: 3
     */
    int y;          // The height of the given grid
    int x;          // The width of the given grid
    char[][] g;     // The given grid, stored to reduce recursion memory usage
    /**
     * Given a 2d grid map of '1's (land) and '0's (water),
     * count the number of islands.
     * <p>
     * This method approaches the problem as one of depth-first connected components search
     *
     * @param grid, the given grid.
     * @return the number of islands.
     */
    public int numIslands(char[][] grid) {
        // Store the given grid
        // This prevents having to make copies during recursion
        this.g = grid;

        // Our count to return
        int c = 0;

        // Dimensions of the given graph
        y = g.length;
        if (y == 0) return 0;
        x = g[0].length;

        // Iterate over the entire given grid
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (g[i][j] == '1') {
                    //dfs返回的时候,就代表一个孤立的块全部被访问了
                    dfs(i, j);
                    c++;
                }
            }
        }
        return c;
    }

    /**
     * 大体思路:先在原地图中找到一个岛屿,然后沿着这个岛屿往上下左右四个方向扩散,此处以一直向右移动举例
     * 1. 右移动一位,发现还是岛屿,那么标记这个岛屿为水('0'),继续往右移动
     * 2. 再次右移动,发现是水(=='0'),那么这条路中断,继续往上或者下(左侧已经被标记为水了,不能回头)
     * 3. 如果上下有不为水的,那么继续之前的步骤,继续移动
     * 4. 如果上下皆为水,那么表示已经探索到了地图的边缘了这块大陆已经探索完毕,可以+1然后退出了
     */
    private void dfs(int i, int j) {
        // Check for invalid indices and for sites that aren't land
        //地图边界之外也视为水
        if (i < 0 || i >= y || j < 0 || j >= x || g[i][j] != '1')
            return;

        // Mark the site as visited
        //逻辑能够到这里只有一种情况 => g[i][j] = '1',改成'0'之后就没有回头路走了
        //只能向其他的方向探索,所以下面虽然走的是四个方向,但是必定有一个方向是死路(来路)
        g[i][j] = '0';

        // Check all adjacent sites
        dfs(i + 1, j);
        dfs(i - 1, j);
        dfs(i, j + 1);
        dfs(i, j - 1);
    }


}
