package com.ld.graph;

import java.util.List;
import java.util.Stack;

/**
 * 深度优先算法可以解决:
 * 1.给定两点是否连通
 * 2.这幅图有多少个可以连通的点(最大值)
 * 3. 如果s - v 连通,找出连通的路径
 */
public class DepthFirstSearch implements Search {
    private final Graph graph;
    private final int source;
    private boolean[] marked;
    private int count;
    private int[] edgeTO;     //这个数组的索引代表了边终点,值代表了边起点,例如s -> v 就是 edgeTo[v] = s

    //这个构造方法很奇特,调用这个构造方法后生成的对象,已经完成了DFS,与指定source连通的所有端点都会被标记
    public DepthFirstSearch(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        marked = new boolean[graph.getV()];  //数组的长度为图的所有顶点数
        dfs(graph, source);
    }

    //dfs的思路是我要尽快完成搜索任务,所以,只要找到一条可以连通的路径就行了
    private void dfs(Graph graph, int source) {
        marked[source] = true;
        count++;
        for (int w : graph.adj(source)) {  //遍历所有与source邻接的点
            if (!marked[w]) {   //如果发现还没有被标记过的点(之前没有访问过),这里如果和w有很多连通的点,那么只会选其中的一条
                edgeTO[w] = source;    // source -> w
                dfs(graph, w);   //那就连到该点上,继续搜索
            }
        }
    }

    //调这个接口,就能够返回v是否和source进行连通
    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    //返回所有source和顶点v之间的中间顶点数量
    @Override
    public int count(int v) {
        return count;
    }

    //返回所有连接source和v的路径,深度优先算法是不是没有办法计算这个?????
    @Override
    public List<List<Integer>> allPath(int s) {
        return null;
    }


    //计算s -> v的一条路径,注意只有一条
    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!this.hasPathTo(v))
            return null;       //如果两个点根本就不连通,那就直接返回不计算了
        Stack<Integer> path = new Stack<>();    //这个为什么要用stack:我们找路径是从终点回溯的,但是路径一般是起点到终点
        //s -> v     edgeTO[v] = s
        int vParent = edgeTO[v];
        path.add(vParent);
        while (vParent != source) {
            vParent = edgeTO[vParent];   //回溯算法,一直回溯到边起点是source为止
            path.push(vParent);
        }
        path.push(source);   //没有这个会少一个起点
        return path;
    }
}
