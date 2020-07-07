package com.soapsnake.algorithms.leetcode.graph.unsolve;

import com.soapsnake.algorithms.structures.unionfind.DSU;

/**
 *
 * Created on 2020-07-07
 */
public class Question684 {
    int MAX_EDGE_VAL = 1000;
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(MAX_EDGE_VAL + 1);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1]))
                return edge;
        }
        throw new AssertionError();
    }
}
