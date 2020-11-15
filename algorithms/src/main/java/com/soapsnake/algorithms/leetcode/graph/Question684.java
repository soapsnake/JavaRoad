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
                //本题目要的就是这个多余的边
                return edge;
        }
        throw new AssertionError();
    }


    public Boolean solution(int size, int[] edges) {
        if (size == 0) {
            return false;
        }
        UnionFind uf = new UnionFind(size);
        for (int i = 0; i < edges.length; i += 2) {
            int from = edges[i];
            int to = edges[i + 1];
            if (!uf.union(from, to)) {
                return true;
            }
        }
        return false;
    }

    //we use union find algorithms to solve this problem
    public class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int _size) {
            parent = new int[_size];
            for (int i = 0; i < _size; i++) {
                //parent = [0,1,2,3,4,5,6....]
                parent[i] = i;
            }
            rank = new int[_size];
        }

        private int find(int x) {
            while (x != parent[x]) {
                x = parent[x];
            }
            return x;
        }

        public boolean union(int x, int y) {
            int xr = find(x), yr = find(y);
            if (xr == yr) {
                //this means x and y already connected,there must be a cycle!
                return false;
            } else if (rank[xr] < rank[yr]) {
                parent[xr] = yr;
            } else if (rank[xr] > rank[yr]) {
                parent[yr] = xr;
            } else {
                parent[yr] = xr;
                rank[xr]++;
            }
            //this means we have connected x and y
            return true;
        }
    }

    public static void main(String[] args) {
        int i = 1024 * 1024;
        System.out.println(i);
    }
}
