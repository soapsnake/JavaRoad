package com.soapsnake.algorithms.leetcode.graph.unsolve;

/**
 * }
 * Created on 2020-07-12
 */
public class Question785 {

    /**
     * graph是一个无向图
     * 回想一下，如果我们可以将图的节点集分割成两个独立的子集A和B，
     * 使图中的每条边在A中都有一个节点，在B中都有另一个节点，那么这个图就是双向的。
     *
     * 基本思路:
     * 如果节点还没上色,那么给它上色并且dfs给它的邻居节点上另外一种色
     * 如果节点本来是一种颜色,dfs校验它的相邻节点是否另一种颜色
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        //This graph might be a disconnected graph. So check each unvisited node.
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !validColor(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean validColor(int[][] graph, int[] colors, int color, int node) {
        if (colors[node] != 0) {
            return colors[node] == color;
        }
        colors[node] = color;
        for (int next : graph[node]) {
            if (!validColor(graph, colors, -color, next)) {
                return false;
            }
        }
        return true;
    }
}
