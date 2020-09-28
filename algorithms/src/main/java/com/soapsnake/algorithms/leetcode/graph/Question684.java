package com.soapsnake.algorithms.leetcode.graph;

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

            //如果对两个顶点进行union时,返回了false,代表两点本来就是联通的,说明这俩点用了多余的边去连接他们
            if (!dsu.union(edge[0], edge[1]))
                return edge;
        }
        throw new AssertionError();
    }
}
