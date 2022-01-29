package com.soapsnake.algorithms.cruel;

import org.junit.Assert;
import org.junit.Test;

import javax.management.QueryEval;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author 
 * Created on 2022-01-26
 */
public class AboutGraph {

    /**
     * 1857
     * 两个问题:
     *  1. 有向无环图如何获取所有的路径
     *
     */
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();  //节点个数
        List<List<Integer>> graph = new ArrayList<>();
        this.buildDirectionGraph(edges, graph, n);
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int found = 0;
        int[][] dp = new int[n][26];
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            found++;
            dp[curNode][colors.charAt(curNode) - 'a']++;

            //拓扑排序的bfs算法
            for (Integer next : graph.get(curNode)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
                for (int i = 0; i < 26; i++) {
                    dp[next][i] = Math.max(dp[next][i], dp[curNode][i]);
                }
            }
        }
        if (found != n) {
            //发现环路
            return -1;
        }

        int res = 0;
        for(int i = 0; i < n; i++) {
            res = Math.max(res, Arrays.stream(dp[i]).max().getAsInt());
        }
        return res;
    }

    /**
     * 模板:构造有向图
     * @param edges 边:edge[0] -> edge[1]
     * @param graph 邻接表,这个数据结构比邻接矩阵简单太多
     * @param nodeCount  顶点个数, 禁止试图用边的个数来计算顶点
     *
     */
    private void buildDirectionGraph(int[][] edges, List<List<Integer>> graph, int nodeCount) {
        for (int i = 0; i < nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
    }

    /**
     * 这个算法其实是判断有向图是否有环路的dfs标准算法
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (dfs(graph, i, color)) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     *  color的三种状态: 0:没访问过 1:有环路,这种只要和它相连的不可能安全了 2.安全点,只要连到他的都是安全的了
     */
    //拓扑排序的dfs算法
    private boolean dfs(int[][] graph, int i, int[] color) {
        if (color[i] > 0) {
            //大于0的都是访问过得,此时进行状态确认,如果之前i这个点被标记过1,说明有环路,这时直接返回false,
            //那么如果被标记过2,说明i连接到了一个安全点,这种情况下,i也是安全的
            return color[i] == 2;
        }
        color[i] = 1;  //这个预标记真的牛逼,如果后面再次遇到一个1,表明遇到了环路
        for (Integer next : graph[i]) {
            if (!dfs(graph, next, color)) {
                return false;
            }
        }
        color[i] = 2;
        return true;
    }

    @Test
    public void testEventual() {
        AboutGraph aboutGraph = new AboutGraph();
        int[][] grapgh = {{1,2},{2,3},{5},{0},{5},{},{}};
        List<Integer> as = aboutGraph.eventualSafeNodes(grapgh);
        System.out.println(as);
    }

    //1591
    public boolean isPrintable(int[][] targetGrid) {
        int nodeCount = 61;
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < nodeCount; i++) {
            this.buildGraph(targetGrid, i, graph, inDegree);
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nodeCount; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            if (!visited.add(curNode)) {
                continue;
            }
            for (Integer next : graph.get(curNode)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return visited.size() == nodeCount;
    }

    private void buildGraph(int[][] targetGrid, int color, List<List<Integer>> graph, int[] inDegree) {
        int maxX = Integer.MIN_VALUE, minX = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE, minY = Integer.MAX_VALUE;
        for (int i = 0; i < targetGrid.length; i++) {
            for (int j = 0; j < targetGrid[0].length; j++) {
                if (targetGrid[i][j] == color) {
                    maxX = Math.max(maxX, i);
                    minX = Math.min(minX, i);
                    maxY = Math.max(maxY, j);
                    minY = Math.min(minY, j);
                }
            }
        }
        if (minX == Integer.MAX_VALUE) {
            return;
        }
        for(int i = minX; i <= maxX; i++) {
            for(int j = minY; j <= maxY; j++) {
                if(targetGrid[i][j] != color) {
                    graph.get(targetGrid[i][j]).add(color);
                    inDegree[color]++;
                }
            }
        }
    }
}
