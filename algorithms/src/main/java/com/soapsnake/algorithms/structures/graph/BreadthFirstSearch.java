package com.soapsnake.algorithms.structures.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch implements GraphSearch {

    private final int source;
    private boolean[] marked;
    private int[] edgeTo;
    private Graph graph;

    public BreadthFirstSearch(Graph graph, int source) {  //传入的graph已经构造完邻接表
        this.graph = graph;
        marked = new boolean[graph.getV()];
        edgeTo = new int[graph.getV()];
        this.source = source;
        bfs(graph, source);  //意义何在?纯粹就是标记下哪些点可以到达吗?
    }

    //bfs和dfs最大的区别,就是bfs会遍历所有和source连通的点,所有可以连通的路径都会走到
    private void bfs(Graph graph, int source) {
        Queue<Integer> queue = new LinkedList<>();  //队列
        marked[source] = true;
        queue.add(source);
        while (!queue.isEmpty()) {
            int v = queue.poll();  //取出并删除队头
            for (int w : graph.adj(v)) {   //graph.adj(v)返回所有从v出发可以到达的点
                if (!marked[w]) {
                    edgeTo[w] = v;  //v -> w
                    marked[w] = true;  //标记w被访问了(可达)
                    queue.add(w);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];   //s -> v是否可达
    }

    @Override
    public int count(int v) {
        return 0;
    }

    @Override
    //s -> v所有可达路径
    public List<List<Integer>> allPath(int v) {
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
        while (vParent != source) {
            vParent = edgeTo[vParent];   //回溯算法,一直回溯到边起点是source为止
            path.push(vParent);
        }
        path.push(source);   //没有这个会少一个起点
        return path;
    }

    @Override
    public int countTotal(List<Integer> vertexs) {
        return 0;
    }
}
