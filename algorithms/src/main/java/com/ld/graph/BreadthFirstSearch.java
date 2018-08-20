package com.ld.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch implements Search {

    private boolean[] marked;
    private int[] edgeTo;
    private final int source;

    public BreadthFirstSearch(Graph graph,int source){
        marked = new boolean[graph.getV()];
        edgeTo = new int[graph.getV()];
        this.source = source;
        bfs(graph, source);
    }

    //bfs和dfs最大的区别,就是bfs会遍历所有和source连通的点,所有可以连通的路径都会走到
    private void bfs(Graph graph, int source) {
        Queue<Integer> queue = new LinkedList<>();  //队列
        marked[source] = true;
        queue.add(source);
        while (!queue.isEmpty()){
            int v = queue.poll();
            for (int w : graph.adj(v)){
                if (!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public int count(int v) {
        return 0;
    }

    @Override
    public List<List<Integer>> allPath(int s) {
        return null;
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!this.hasPathTo(v))
            return null;       //如果两个点根本就不连通,那就直接返回不计算了
        Stack<Integer> path = new Stack<>();    //这个为什么要用stack:我们找路径是从终点回溯的,但是路径一般是起点到终点
        //s -> v     edgeTO[v] = s
        int vParent = edgeTo[v];
        path.add(vParent);
        while (vParent != source){
            vParent = edgeTo[vParent];   //回溯算法,一直回溯到边起点是source为止
            path.push(vParent);
        }
        path.push(source);   //没有这个会少一个起点
        return path;
    }
}
