package com.soapsnake.algorithms.leetcode.graph.unsolve;

import java.awt.image.Kernel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.junit.Test;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-07-08
 */
public class Question743 {


    /**
     *
     * @param times  graph
     * @param N   source
     * @param K   dest
     * @return    total weight
     */
    //leetcode743
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
        for (int[] nodes : times) {
            int from = nodes[0];
            int to = nodes[1];
            int weight = nodes[2];
            adj.putIfAbsent(from, new HashMap<>());
            adj.get(from).put(to, weight);
        }
        return this.dfs(adj, K, N, 0, new HashSet<>());
    }

    private int dfs(Map<Integer, Map<Integer, Integer>> adj, int source, int end, int weight, Set<Integer> cache) {
        if (!adj.containsKey(source)) {
            return -1;
        }
        if (source == end) {
            return weight;
        }
        Map<Integer, Integer> nexts = adj.get(source);
        if (nexts == null) {
            return -1;
        }
        for (Integer next : nexts.keySet()) {
            int res = this.dfs(adj, next, end, weight + nexts.get(next), cache);
            if (res != -1) {
                return res;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4;
        int K = 2;
        System.out.println(networkDelayTime(times, N, K));

    }
}
