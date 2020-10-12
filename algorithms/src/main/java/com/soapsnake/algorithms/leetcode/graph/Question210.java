package com.soapsnake.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode210 拓扑排序
public class Question210 {
    static int WHITE = 1;  //表示没有访问过
    static int GRAY = 2;   //表示节点被访问过,但是有可能带环也有可能不带
    static int BLACK = 3;  //表示节点已经被访问过且不带环

    boolean isPossible;  //为false时表明带有环路
    Map<Integer, Integer> color;  //用这个来表示节点已经访问过了?
    Map<Integer, List<Integer>> adjList;  //邻接表
    List<Integer> topologicalOrder;

    private void init(int numCourses) {
        this.isPossible = true;  //默认不带环
        this.color = new HashMap<>();
        this.adjList = new HashMap<>();
        this.topologicalOrder = new ArrayList<>();

        // By default all vertces are WHITE
        for (int i = 0; i < numCourses; i++) {
            //所有节点初始化成为白色
            this.color.put(i, WHITE);
        }
    }

    //这个dfs算法的详细解释可见question802,其实就是探测图中有没有环路
    private void dfs(int node) {
        // Don't recurse further if we found a cycle already
        if (!this.isPossible) {
            return;
        }

        // Start the recursion
        this.color.put(node, GRAY); //访问过的节点修改成灰色

        // Traverse on neighboring vertices
        for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<>())) {
            if (this.color.get(neighbor) == WHITE) {
                //如果邻居节点没有处理过,那么递归处理之
                //如果是有向图,从A -> B 走完会走 A -> C, 不会出现重复路线(再次重申是有向图)
                //那么哪种情况会一个点访问两次了? B -> A,这就相当于A和B是双向联通的,但是这就构成了环路
                this.dfs(neighbor);
            } else if (this.color.get(neighbor) == GRAY) {
                // An edge to a GRAY vertex represents a cycle
                //这个节点链接到了一个灰色的节点上,表明该节点和这个邻居节点是双向联通的,也就是带环
                this.isPossible = false;  //图带有环路
            }
        }

        // Recursion ends. We mark it as black
        this.color.put(node, BLACK);  //处理完了把节点标记为黑色(节点状态: 白 -> 灰 -> 黑)

        //第一次到这里时,node一定是图的最后一个节点,也就是不依赖任何其他节点的点(没有出度)
        //所以如果是 A -> B -> C 那么这个topologicalOrder一定是C,B,A
        this.topologicalOrder.add(node);
    }

    //这道题实际考察拓扑排序
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //课程数量就是节点的数量
        this.init(numCourses);

        // Create the adjacency list representation of the graph
        //这里完成有向图的构造,邻接表使用有序list表示真的没有问题吗?还有,这里构造有向图为什么不放在初始化里面做了???
        for (int[] prerequisite : prerequisites) {
            //课程A依赖课程B,那么这条有向边实际上是从B指向A的
            int dest = prerequisite[0];
            int src = prerequisite[1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());
            lst.add(dest);
            adjList.put(src, lst);
        }

        // If the node is unprocessed, then call dfs on it.
        for (int i = 0; i < numCourses; i++) {
            if (this.color.get(i) == WHITE) {  //如果节点还是白的,说明没有访问过,那么处理这个节点
                this.dfs(i);
            }
        }
        //经过上述dfs算法后,我们已经知道图中是否有环路,哪些顶点是环路中的顶点

        int[] order;  //最终结果
        if (this.isPossible) {  //到这个if时,node指向最后一个节点,这个isPossible表示的应该是最后一个节点的可行性
            order = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                //topologicalOrder为什么要倒着往前取数???
                order[i] = this.topologicalOrder.get(numCourses - i - 1);
            }
        } else {
            order = new int[0];   //为什么带环就直接判定不可拓扑排序?
        }

        return order;
    }
}
