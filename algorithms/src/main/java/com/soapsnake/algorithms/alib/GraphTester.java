package com.soapsnake.algorithms.alib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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


    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (equations.length == 0 || values == null || values.length == 0 || queries.length == 0) {
            return null;
        }
        Map<String, Map<String, Double>> adj = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String source = equations[i][0];
            String end = equations[i][1];
            double weight = values[i];
            adj.putIfAbsent(source, new HashMap<>());
            adj.get(source).put(end, weight);

            adj.putIfAbsent(end, new HashMap<>());
            adj.get(end).put(source, 1 / weight); //a->b=k, b->a=1/k
        }
        System.out.println(adj);
        double[] res = new double[queries.length];
        int i = 0;
        for (String[] strings : queries) {
            String source = strings[0];
            String end = strings[1];
            res[i++] = dfsGraph(adj, source, end, 1, new HashSet<>());//每一次运算一个cache
        }
        return res;
    }

    private double dfsGraph(Map<String, Map<String, Double>> adj, String source, String end, double weight, Set<String> cache) {
        if (adj.get(source) == null) {
            return -1;
        }
        if (!cache.add(source)) {
            return -1;
        }
        if (source.equals(end)) {
            //两点联通,这种情况可以返回结果
            return weight;
        }
        for (String next : adj.get(source).keySet()) {
            double res = dfsGraph(adj, next, end, weight * adj.get(source).get(next), cache);
            if (res != -1) { //res=-1代表两点不连通或者出现了环路
                return res;
            }
        }
        return -1;
    }

    @Test
    /**
     * equations = [ ["a", "b"], ["b", "c"] ],
     * values = [2.0, 3.0],
     * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
     * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
     */
    public void testcalcEquation() {
        String[][] equations = {{"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        String[][] queries1 = {{"b", "a"}};
        System.out.println(Arrays.toString(calcEquation(equations, values, queries)));
    }

    //求0 -> i-1的所有联通路径
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (graph == null || graph.length == 0) {
            return res;
        }
        int last = graph.length - 1;
        this.dfs1(graph, 0, last, res, new ArrayList<>());
        return res;
    }

    private void dfs1(int[][] graph, int from, int last, List<List<Integer>> res, List<Integer> path) {
        if (from == last) {
            res.add(path);
        }
        path.add(from);
        int[] nexts = graph[from];
        for (int next : nexts) {
            List<Integer> copyPaths = new ArrayList<>(path);
            dfs1(graph, next, last, res, copyPaths);
        }
    }

    @Test
    public void testALlpath() {
        int[][] graph = {{1,2}, {3}, {3}, {}};
        System.out.println(allPathsSourceTarget(graph));
    }


}

