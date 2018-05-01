package com.ld.graph;

import java.util.List;

/**
 * 深度优先算法
 */
public class DepthFirstSearch implements Search {

    private boolean[] marked;

    private int count;

    @Override
    public void search(Graph graph, int v) {
        marked = new boolean[graph.getV()];
        dfs(graph, v);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        count++;
        for (int w : graph.adj(v)){
            if (!marked[w]){
                dfs(graph, w);
            }
        }
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    @Override
    public int count(int v) {
        return count;
    }

    @Override
    public List<List<Integer>> allPath(int v, int s) {
        return null;
    }
}
