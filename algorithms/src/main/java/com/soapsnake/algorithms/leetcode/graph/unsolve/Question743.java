package com.soapsnake.algorithms.leetcode.graph.unsolve;

import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;

import org.junit.Test;

/**
 * }
 * Created on 2020-07-08
 */
public class Question743 {


    /**
     * 这题实际上求的是图中任意两点的最短路径
     * @param times graph
     * @param N total node size
     * @param K source node
     * @return total weight
     */
    //leetcode743
    public int networkDelayTime(int[][] times, int N, int K) {
        if (times == null || times.length == 0) {
            return -1;
        }
        // store the source node as key. The value is another map of the neighbor nodes and distance.
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int distance = time[2];
            adj.putIfAbsent(from, new HashMap<>());
            adj.get(from).put(to, distance);
        }

        //Use PriorityQueue to get the node with shortest absolute distance
        //and calculate the absolute distance of its neighbor nodes.
        Map<Integer, Integer> distanceMap = new HashMap<>();
        distanceMap.put(K, 0);
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> i1[1] - i2[1]);  //小顶堆
        pq.offer(new int[] {K, 0});
        int max = -1;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); //距离短的节点会先被poll出来
            int curFrom = cur[0];   //from节点
            int distance = cur[1];
            // Ignore processed nodes
            if (distanceMap.containsKey(curFrom) && distanceMap.get(curFrom) < distance) {
                continue;
            }

            Map<Integer, Integer> toMap = adj.get(curFrom);
            if (toMap == null) {
                continue;
            }
            for (Map.Entry<Integer, Integer> toEntry : toMap.entrySet()) {
                int absoluteDistence = distance + toEntry.getValue();
                int toNode = toEntry.getKey();
                if (distanceMap.containsKey(toNode) && distanceMap.get(toNode) <= absoluteDistence) {
                    continue;
                }
                distanceMap.put(toNode, absoluteDistence);
                pq.offer(new int[] {toNode, absoluteDistence});
            }
        }
        // get the largest absolute distance.
        for (int val : distanceMap.values()) {
            if (val > max) {
                max = val;
            }
        }
        return distanceMap.size() == N ? max : -1;
    }

    Map<Integer, Integer> dist;
    public int networkDelayTime2(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[2], edge[1]});  // 0-----weight(2)------->1
        }
        for (int node: graph.keySet()) {
            Collections.sort(graph.get(node), (a, b) -> a[0] - b[0]); //0是distance,别搞错了
        }
        dist = new HashMap<>();
        for (int node = 1; node <= N; ++node)
            dist.put(node, Integer.MAX_VALUE);  //node -> distance

        dfs(graph, K, 0);

        //到这里,dist记录了每个点出边当中最短的一条
        int ans = 0;
        for (int cand: dist.values()) {
            if (cand == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, cand);
        }
        return ans;
    }

    public void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed) {
        //也就是说,只有node的出边距离最小的边才能进入dfs????
        if (elapsed >= dist.get(node)) return;
        dist.put(node, elapsed);
        if (graph.containsKey(node))
            for (int[] info: graph.get(node))
                dfs(graph, info[1], elapsed + info[0]);
    }

    @Test
    public void test() {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4;
        int K = 2;
        System.out.println(networkDelayTime(times, N, K));

    }
}
