package com.soapsnake.algorithms.leetcode.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-08-09
 */
public class Question994 {

    //leetcode994 图的bfs算法
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count_fresh = 0;
        //Put the position of all rotten oranges in queue
        //count the number of fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    //如果是烂橘子,那么把烂橘子的坐标加入队列
                    queue.offer(new int[] {i, j});
                } else if (grid[i][j] == 1) {
                    //如果是新鲜橘子只记录数量
                    count_fresh++;
                }
            }
        }
        //if count of fresh oranges is zero --> return 0
        if (count_fresh == 0)
            return 0;
        int count = 0;

        //四个方向
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        //bfs starting from initially rotten oranges
        while (!queue.isEmpty()) {
            ++count;
            int size = queue.size();  //第一批烂橘子的数量
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll(); //烂橘子坐标
                for (int dir[] : dirs) {
                    //这种移动的方式属实牛逼
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    //if x or y is out of bound
                    //or the orange at (x , y) is already rotten
                    //or the cell at (x , y) is empty
                    //we do nothing
                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2)
                        continue;
                    //mark the orange at (x , y) as rotten
                    grid[x][y] = 2;
                    //put the new rotten orange at (x , y) in queue
                    queue.offer(new int[] {x, y});
                    //decrease the count of fresh oranges by 1
                    count_fresh--;
                }
            }
        }
        //-1的情况是至少一个新鲜橘子是孤岛,不可能被烂橘子传染了
        return count_fresh == 0 ? count - 1 : -1;
    }
}
