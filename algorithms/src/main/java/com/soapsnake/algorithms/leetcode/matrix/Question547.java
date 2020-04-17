package com.soapsnake.algorithms.leetcode.matrix;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-04-17
 */
public class Question547 {


    //与question 200的区别是什么?为什么不能用200的解法?
    //leetcode547
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }

}
