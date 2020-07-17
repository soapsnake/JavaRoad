package com.soapsnake.algorithms.leetcode.graph.unsolve;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.soapsnake.algorithms.structures.unionfind.DSU;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-07-15
 */
public class Question802 {

    //实际上是探测有向图graph是否有环路,返回所有无回路的顶点
    //question802
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        if (graph == null || graph.length == 0)
            return res;

        int nodeCount = graph.length;
        int[] color = new int[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            if (dfs(graph, i, color))
                res.add(i);
        }
        return res;
    }

    /**
     * 0:have not been visited
     * 1:safe
     * 2:unsafe
     */
    public boolean dfs(int[][] graph, int from, int[] color) {
        //如果from节点已经访问过了,那么这个节点的安全还是不安全是已知的
        if (color[from] != 0)
            return color[from] == 1;

        //到这里说明from节点未知,那么先标记其为不安全
        color[from] = 2;

        for (int to : graph[from]) {
            if (!dfs(graph, to, color))
                return false;
        }
        //能到这里,说明from节点的所有邻居节点都是安全的,那么from节点也是安全的,标记为1,并且返回true
        color[from] = 1;
        return true;
    }
}
