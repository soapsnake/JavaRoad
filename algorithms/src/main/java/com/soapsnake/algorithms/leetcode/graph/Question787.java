package com.soapsnake.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Created on 2020-03-20
 */
public class Question787 {

    /**
     * Example 2:
     * Input:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 0
     * Output: 500
     * Explanation:
     * The graph looks like this:
     * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
     */
    private int dfs_min = Integer.MAX_VALUE;
    private int dst;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] ints : flights) {
            map.putIfAbsent(ints[0], new ArrayList<>());
            map.get(ints[0]).add(new int[] {ints[1], ints[2]});
        }
        this.dst = dst;
        this.dfsGraph(map, src, K, 0);
        return dfs_min == Integer.MAX_VALUE ? -1 : dfs_min;
    }

    private void dfsGraph(Map<Integer, List<int[]>> map, int src, int k, int curTotal) {
        if (src == dst) {
            dfs_min = curTotal;
            return;
        }
        if (k < 0) {
            return;
        }
        if (map.get(src) == null) {
            return;
        }
        for (int[] ints : map.get(src)) {
            if (ints[1] + curTotal > dfs_min) {
                continue;
            }
            dfsGraph(map, ints[0], --k, curTotal + ints[1]);
        }
    }

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        int bfs_min = Integer.MAX_VALUE;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] ints : flights) {
            map.putIfAbsent(ints[0], new ArrayList<>());
            map.get(ints[0]).add(new int[] {ints[1], ints[2]});
        }
        Queue<int[]> que = new LinkedList<>();
        int step = 0;
        que.offer(new int[] {src, 0});
        //图的bfs算法和树思想是一致的,树的时候,这个queue的一批次poll出的是树的一层,但是图的话,批次poll出的就是一个端点的所有邻居节点(会不会重复了?)
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int[] cur = que.poll();
                int curPoint = cur[0];
                int curPrice = cur[1];

                if (curPoint == dst) {
                    bfs_min = Math.min(bfs_min, curPrice);
                }
                if (map.get(curPoint) == null) {
                    continue;
                }
                for (int[] ints : map.get(curPoint)) {
                    if (ints[1] + curPrice > bfs_min) {
                        continue;
                    }
                    que.offer(new int[] {ints[0], ints[1]});
                }
            }
            if (step++ > K) {
                break;
            }
        }
        return bfs_min == Integer.MAX_VALUE ? -1 : bfs_min;
    }

    //Dijkstra 算法
    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] f : flights) {
            map.putIfAbsent(f[0], new ArrayList<>());
            map.get(f[0]).add(new int[] {f[1], f[2]});
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        q.offer(new int[] {0, src, K + 1});
        while (!q.isEmpty()) {
            int[] c = q.poll();
            int cost = c[0];
            int curr = c[1];
            int stop = c[2];
            if (curr == dst)
                return cost;
            if (stop > 0) {
                if (!map.containsKey(curr))
                    continue;
                for (int[] next : map.get(curr)) {
                    q.add(new int[] {cost + next[1], next[0], stop - 1});
                }
            }
        }
        return -1;
    }
}
