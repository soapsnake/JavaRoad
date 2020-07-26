package com.soapsnake.algorithms.leetcode.graph.theme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-07-24
 */
public class Question797 {

    //leetcode797 有向图两点间全路径
    //解法1:dfs
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

    //解法2:bfs
    public List<List<Integer>> allPathsSourceTargetBfs(int[][] graph) {
        List<List<Integer>> result = new ArrayList();
        Queue<List<Integer>> queue = new LinkedList();
        queue.add(Arrays.asList(0));
        int destinationVertex = graph.length - 1;

        while(!queue.isEmpty()) {
            List<Integer> pathSoFar = queue.poll();
            int currentVertex = pathSoFar.get(pathSoFar.size() - 1);
            // check if currentVertex is destinationVertex add pathSoFar in result
            if(currentVertex == destinationVertex) result.add(new ArrayList(pathSoFar));
            for(int v : graph[currentVertex]) {
                List<Integer> newPath = new ArrayList(pathSoFar);
                newPath.add(v);
                queue.add(newPath);
            }
        }

        return result;
    }

    //解法3:backTrace
    public List<List<Integer>> allPathsSourceTargetBackTrace(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        backtracking(res, graph, 0, path);
        return res;
    }

    private void backtracking(List<List<Integer>> res, int[][] graph, int start, List<Integer> cur) {
        if(graph[start].length==0) {
            if(start==graph.length-1) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }

        for(int next:graph[start]) {
            cur.add(next);
            backtracking(res, graph, next, cur);
            cur.remove(cur.size()-1);
        }
    }
}

