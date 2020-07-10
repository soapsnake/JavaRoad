package com.soapsnake.algorithms.leetcode.graph;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-07-06
 */
public class Question684 {

    /**
     * 移除一个图中的一些边,使得这个无向图能够变成一个树
     * 思路:链表??
     */
    int MAX_EDGE_VAL = 1000;
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(MAX_EDGE_VAL + 1);
        for (int[] edge : edges) {
            //如果发现一个边的两个点是不union的,那么就是所求的边?
            if (!dsu.union(edge[0], edge[1]))
                return edge;
        }
        throw new AssertionError();
    }

    class DSU {
        int[] parent;
        int[] rank;
        public DSU(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++)
                parent[i] = i;
            rank = new int[size];
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean union(int x, int y) {
            int xr = find(x), yr = find(y);
            if (xr == yr) {
                return false;
            } else if (rank[xr] < rank[yr]) {
                parent[xr] = yr;
            } else if (rank[xr] > rank[yr]) {
                parent[yr] = xr;
            } else {
                parent[yr] = xr;
                rank[xr]++;
            }
            return true;
        }
    }

    @Test
    public void test() {
        int[][] graph = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        System.out.println(Arrays.toString(findRedundantConnection(graph)));
    }

}
