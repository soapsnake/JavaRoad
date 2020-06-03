package com.soapsnake.algorithms.leetcode.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Question207 {
    //todo 图的边集表示法弄懂了先: https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs

    /**
     * According to the Wiki about what Topological sorting is (https://en.wikipedia.org/wiki/Topological_sorting)
     * and the Kahn's algorithm as shown below:
     * alt text
     * L ← Empty list that will contain the sorted elements
     * S ← Set of all nodes with no incoming edges
     * while S is non-empty do
     * <p>
     * remove a node n from S
     * add n to tail of L
     * for each node m with an edge e from n to m do
     * remove edge e from the graph
     * if m has no other incoming edges then
     * insert m into S
     * if graph has edges then
     * return error (graph has at least one cycle)
     * else
     * return L (a topologically sorted order)
     */
    //leetcode207
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] incomingEdges = new int[numCourses];
        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < adj.length; i++) {
            //所有图的初始化,这一步都是必不可少的
            adj[i] = new LinkedList<>();
        }
        //每一对pair的长度都是2,正好对应一个边的两个顶点
        for (int[] pair : prerequisites) {
            //是不是只有有向图才有出度和入度的概念
            incomingEdges[pair[0]]++;

            //有向图和无向图最大的区别就是在这里,如果是无向图,这里要两个顶点add两次
            //这里好像是反的 pair[0]依赖pair[1], 按理应该是0 -> 1, 也就是被依赖的点记录了所有依赖它的点
            adj[pair[1]].add(pair[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < incomingEdges.length; i++) {
            if (incomingEdges[i] == 0) {
                queue.add(i);
            }
        }
        int edgeCnt = prerequisites.length; //依赖数量
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int goCrs : adj[cur]) {
                edgeCnt--;  //依赖数量递减
                if (--incomingEdges[goCrs] == 0)
                    queue.add(goCrs);
            }
        }
        return edgeCnt == 0;
    }

    public static void main(String[] args) {
        int i = 6;
        i += i - 1;
        System.out.println(i);
    }
}
