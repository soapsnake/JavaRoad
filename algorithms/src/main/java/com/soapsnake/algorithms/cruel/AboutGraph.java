package com.soapsnake.algorithms.cruel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

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

    //310
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //思路: 构建图,
        //然后遍历每一个node进行dfs,dfs的同时记录每一条路径的长度, node -> path,最后比较哪个node的path最短
        if (n == 1) {
            return Arrays.asList(0);
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[n];

        //无向图标准构建方法,与有向图的区别是要add两次
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            inDegree[edge[0]]++;
            inDegree[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 1) {
                queue.offer(i);  //入度为1的node其实是最外层的叶子节点,我们从外向内就像剥洋葱一样
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            res = new ArrayList<>();  //res起到暂存的作用,最后保留的就是圆心的节点
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                res.add(cur);
                for (int next : graph.get(cur)) {
                    inDegree[next]--;
                    if (inDegree[next] == 1) {
                        queue.offer(next);
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void testFind() {
        AboutGraph a = new AboutGraph();
        int n = 4;
        int[][] edges = {{1,0}, {1,2}, {1,3}};
        System.out.println(a.findMinHeightTrees(n, edges));
    }


    public int maximumInvitations(int[] favorite) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < favorite.length; i++) {
            graph.add(new ArrayList<>());
        }

        int answer = 0;

        List<List<Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < favorite.length; i++) {
            if (i == favorite[favorite[i]]) {
                if (i < favorite[i]) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(favorite[i]);
                    pairs.add(pair);
                }
            } else {
                graph.get(favorite[i]).add(i);
            }
        }

        boolean[] visited = new boolean[favorite.length];
        for (List<Integer> pair: pairs) {
            answer += dfs(graph, pair.get(0), visited) + dfs(graph, pair.get(1), visited);
        }

        int[] counter = new int[favorite.length];
        int[] round = new int[favorite.length];

        int rnd = 1;

        int circleMax = 0;

        for (int i = 0; i < favorite.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (round[i] != 0) {
                continue;
            }

            int cnt = 1;
            int j = i;
            while (counter[j] == 0) {
                counter[j] = cnt;
                round[j] = rnd;
                j = favorite[j];
                cnt++;
            }
            if (round[j] == rnd) {
                circleMax = Math.max(circleMax, cnt - counter[j]);
            }
            rnd++;
        }
        return Math.max(circleMax, answer);
    }

    private int dfs(List<List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        int max = 0;
        for (int neighbor: graph.get(node)) {
            max = Math.max(max, dfs(graph, neighbor, visited));
        }
        return max + 1;
    }


    public int maximumANDSum(int[] nums, int numSlots) {
        int ans = 0;
        int[] f = new int[1 << (numSlots * 2)];
        for ( int i = 0; i < f.length; i++) {
            int c = Integer.bitCount(i);
            if (c >= nums.length) {
                continue;
            }
            for (int j = 0; j < numSlots * 2; ++j) {
                if ((i & (1 << j)) == 0) {
                    int s = i | (1 << j);
                    f[s] = Math.max(f[s], f[i] + ((j / 2 + 1) & nums[c]));
                    ans = Math.max(ans, f[s]);
                }
            }
        }
        return ans;
    }
}
