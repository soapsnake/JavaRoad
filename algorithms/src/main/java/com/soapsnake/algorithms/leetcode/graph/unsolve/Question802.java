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
    public List<Integer> eventualSafeNodes(int[][] graph) {
        Set<Integer> res = new HashSet<>();
        DSU dsu = new DSU(graph.length);
        for (int[] edge : graph) {
            int from = edge[0];
            int to = edge[1];
            if (dsu.union(from, to)) {
                res.add(from);
                res.add(to);
            }
        }
        return new ArrayList<>(res);
    }
}
