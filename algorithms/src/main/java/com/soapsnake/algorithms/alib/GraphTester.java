package com.soapsnake.algorithms.alib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;

import com.soapsnake.algorithms.structures.graph.Graph;
import com.soapsnake.algorithms.structures.graph.direct.DirectWeightGraph;
import com.soapsnake.algorithms.structures.graph.direct.Vertex;
import com.soapsnake.algorithms.structures.graph.undirect.UndirectGraph;
import org.junit.Test;

public class GraphTester {

    /**
     * 搜索图的两点最短路径
     */
    public int shortestPath(UndirectGraph undirectGraph, Vertex start, Vertex end) {
        Graph graph = DirectWeightGraph.makeWeightGraphForTest();


        return 0;
    }

    @Test
    public void testShortestPath() {

    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<Integer>[] adj = new List[flights.length];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        Map<int[], Integer> priceMap = new HashMap<>();
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            List<Integer> lists = adj[from];
            if (!lists.contains(to)) {
                lists.add(to);
            }
            int[] edge = new int[2];
            edge[0] = from;
            edge[1] = to;
            priceMap.put(edge, price);
        }

        List<List<Integer>> res = new ArrayList<>();
        trackTrace(adj, res, new ArrayList<>(), src, dst);
        if (res.isEmpty()) {
            return -1;
        }
        System.out.println("adj = " + Arrays.toString(adj));
        System.out.println("res = " + res);
        System.out.println("map = " + priceMap);
        return findMinPrice(res, priceMap);
    }

    private int findMinPrice(List<List<Integer>> res, Map<int[], Integer> priceMap) {
        int min = Integer.MAX_VALUE;
        for (List<Integer> list : res) {
            int lun = 0;
            for (int i = 1; i < list.size(); i++) {
                int curPrice = 0;
                int[] temp = new int[2];
                temp[0] = list.get(i - 1);
                temp[1] = list.get(i);
                lun += priceMap.get(temp);
            }
            min = Math.min(lun, min);
        }
        return min;
    }

    private void trackTrace(List<Integer>[] adj, List<List<Integer>> res, List<Integer> tmp, int cur, int dst) {
        if (adj[cur].contains(dst)) {
            tmp.add(cur);
            tmp.add(dst);
            res.add(tmp);
        }
        for (int v : adj[cur]) {
            tmp.add(v);
            trackTrace(adj, res, tmp, v, dst);
            tmp.remove(tmp.size() - 1);
        }
    }

    @Test
    public void testFindPrice() {
        int n = 3;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0;
        int dst = 2;
        int K = 1;
        System.out.println(findCheapestPrice(n, flights, src, dst, K));
    }
}
