package com.soapsnake.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
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
            if (c != null)
                return this.id == c.id;
            return false;
        }

        public int compareTo(City c) {
            return this.costFromSrc - c.costFromSrc;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] adj = new int[n][n];  //邻接矩阵
        for (int[] flight : flights) {
            adj[flight[0]][flight[1]] = flight[2];   //加权有向图的权重表示法
        }

        //默认小顶堆,堆顶元素最小
        PriorityQueue<City> minHeap = new PriorityQueue<>();
        minHeap.offer(new City(src, 0, 0)); //起点

        int[] cost = new int[n];  //n为顶点的个数
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
            int[] nexts = adj[curCity.id];
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

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        queue.offer(1);
        queue.offer(2);
        queue.offer(834);
        queue.offer(43);
        queue.offer(3);
        queue.offer(92);
        queue.offer(10);
        queue.offer(123);
        queue.offer(15);
        queue.offer(20);
        queue.offer(100);
        queue.offer(120);
        System.out.println(queue);
        System.out.println("toArray = " + Arrays.toString(queue.toArray()));

        System.out.println("now we gonna poll element from the queue------");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    /**
     * 标准dfs解法
     */
    int ans_dfs;
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        ans_dfs = Integer.MAX_VALUE;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] i : flights) {
            map.putIfAbsent(i[0], new ArrayList<>());
            map.get(i[0]).add(new int[] {i[1], i[2]});
        }
        dfs(map, src, dst, K + 1, 0);
        return ans_dfs == Integer.MAX_VALUE ? -1 : ans_dfs;
    }

    public void dfs(Map<Integer, List<int[]>> map, int src, int dst, int k, int cost) {
        if (k < 0)
            return;
        if (src == dst) {
            //dst是恒定不变的
            ans_dfs = cost;
            return;
        }
        if (!map.containsKey(src)) {
            return;
        }
        for (int[] i : map.get(src)) {
            //这个continue可以说是这个解法的核心
            if (cost + i[1] > ans_dfs)
                continue;
            dfs(map, i[0], dst, k - 1, cost + i[1]);
        }
    }

    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int K) {
        //todo bfs解法

        return 0;
    }

    public int findCheapestPrice4(int n, int[][] flights, int src, int dst, int K) {
        //todo Dijkstra解法

        return 0;
    }
}
