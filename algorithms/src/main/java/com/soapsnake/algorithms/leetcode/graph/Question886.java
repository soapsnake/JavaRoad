package com.soapsnake.algorithms.leetcode.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * Created on 2020-05-27
 */
public class Question886 {

    //leetcode886
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] color = new int[N + 1];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            //如果没有这个初始化的过程,下面的迭代无法赋值将无法进行
            adj.add(new ArrayList<>());
        }
        for (int[] dislike : dislikes) {
            adj.get(dislike[0]).add(dislike[1]);
            //无向图,最容易遗漏这里,如果不加这个有些顶点就只进不出了
            adj.get(dislike[1]).add(dislike[0]);
        }
        for (int i = 1; i <= N; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                while (!queue.isEmpty()) {
                    //实际上是图的dfs算法
                    int from = queue.poll();
                    List<Integer> nabours = adj.get(from);
                    for (Integer to : nabours) {
                        if (color[to] == 0) {
                            color[to] = color[from] == 1 ? 2 : 1;
                            queue.add(to);
                        } else {
                            if (color[to] == color[from]) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
