package com.soapsnake.algorithms.leetcode.graph.unsolve;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.soapsnake.algorithms.structures.unionfind.DSU;

import sun.font.TrueTypeFont;

/**
 * }
 * Created on 2020-07-15
 */
public class Question802 {

    //实际上是探测有向图graph是否有环路,返回所有无回路的顶点
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        if (graph == null || graph.length == 0)
            return res;
        //思路,节点的三种状态:0,未访问, 1,已访问,安全节点 2,未访问,不安全节点
        //先把节点标记为不安全,如果节点dfs后发现所有相邻节点都是安全的,那么再把它改成安全的
        int nodeCount = graph.length;
        int[] status = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            if (this.dfs(i, graph, status)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean dfs(int from, int[][] graph, int[] status) {
        if (status[from] != 0) {
            return status[from] == 1;
        }
        status[from] = 2;
        for (int to : graph[from]) {
            if (!dfs(to, graph, status)) {
                return false;
            }
        }
        //只有dfs结束,所有相邻节点都探测完毕才能修改节点的安全状态
        status[from] = 1;
        return true;
    }
}
