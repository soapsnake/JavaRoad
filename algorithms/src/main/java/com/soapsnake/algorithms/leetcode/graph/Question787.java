package com.soapsnake.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
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
    //leetcode 787
    private class City implements Comparable<City> {
        int id;
        int costFromSrc;
        int stopFromSrc;

        public City(int id, int costFromSrc, int stopFromSrc) {
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.stopFromSrc = stopFromSrc;
        }

        public boolean equals(City c) {
            if (c != null) {
                return this.id == c.id;
            }
            return false;
        }

        public int compareTo(City c) {
            return this.costFromSrc - c.costFromSrc;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] srcToDst = new int[n][n];
        for (int[] flight : flights) {
            srcToDst[flight[0]][flight[1]] = flight[2];
        }

        PriorityQueue<City> minHeap = new PriorityQueue<>();
        minHeap.offer(new City(src, 0, 0));

        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        int[] stop = new int[n];
        Arrays.fill(stop, Integer.MAX_VALUE);
        stop[src] = 0;

        while (!minHeap.isEmpty()) {
            City curCity = minHeap.poll();
            if (curCity.id == dst) {
                return curCity.costFromSrc;
            }
            if (curCity.stopFromSrc == K + 1) {
                continue;
            }
            int[] nexts = srcToDst[curCity.id];
            for (int i = 0; i < n; i++) {
                if (nexts[i] != 0) {
                    int newCost = curCity.costFromSrc + nexts[i];
                    int newStop = curCity.stopFromSrc + 1;
                    if (newCost < cost[i]) {
                        minHeap.offer(new City(i, newCost, newStop));
                        cost[i] = newCost;
                    } else if (newStop < stop[i]) {
                        minHeap.offer(new City(i, newCost, newStop));
                        stop[i] = newStop;
                    }
                }
            }
        }
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }

    //dfs算法解决这个题

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        this.dst = dst;
        for (int[] ints : flights) {
            map.putIfAbsent(ints[0], new ArrayList<>());
            map.get(ints[0]).add(new int[] {ints[1], ints[2]});
        }
        this.dfsGraph(map, src, K, 0);
        return dfs_min == Integer.MAX_VALUE ? -1 : dfs_min;
    }

    private int dfs_min = Integer.MAX_VALUE;
    private int dst;

    private void dfsGraph(Map<Integer, List<int[]>> map, int src, int k, int price) {
        if (k < 0) {
            return;
        }
        if (src == dst) {
            this.dfs_min = price;
            return;
        }
        if (!map.containsKey(src)) {
            return;
        }
        for (int[] list : map.get(src)) {
            if (price + list[1] > dfs_min) {
                continue;
            }
            dfsGraph(map, list[0], k - 1, price + list[1]);
        }
    }

    //bfs:
    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] ints : flights) {
            map.putIfAbsent(ints[0], new ArrayList<>());
            map.get(ints[0]).add(new int[] {ints[1], ints[2]});
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {src, 0});
        int bfs_min = Integer.MAX_VALUE;
        int step = 0;
        while (!queue.isEmpty()) {
            //这里的一批端点的含义一定要搞清楚
//            step++;     //错误点3,不能再这里递增step的值
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int curPoint = cur[0];   //当前节点
                int curTotal = cur[1];  //当前总价
                if (curPoint == dst) {
                    bfs_min = Math.min(curTotal, bfs_min);
//                    break;          //错误点1,不能break,为什么???
                }
                if (map.get(curPoint) == null) {
//                    break;
                    continue;    //错误点2,不能break
                }
                for (int[] ints : map.get(curPoint)) {
                    int nextPoint = ints[0];
                    int nextPrice = ints[1];
                    if (curTotal + nextPrice > bfs_min) {
                        continue;
                    }
                    queue.add(new int[] {nextPoint, curTotal + nextPrice});
                }
            }
            if (step++ > K) {
                break;
            }
        }
        return bfs_min == Integer.MAX_VALUE ? -1 : bfs_min;
    }
}
