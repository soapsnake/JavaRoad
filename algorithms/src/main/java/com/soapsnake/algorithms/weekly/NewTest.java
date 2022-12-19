package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-08-26
 * JavaRoad
 */
public class NewTest {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Set<Integer>> subTree = new HashMap<>();

    public int minimumScore(int[] nums, int[][] edges) {
        int total = 0;
        int n = nums.length;
        cache = new int[n];
        for (int i = 0; i < nums.length; ++i) {
            total = total ^ nums[i];
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            List<Integer> list = graph.getOrDefault(u, new ArrayList<>());
            list.add(v);
            graph.put(u, list);
            list = graph.getOrDefault(v, new ArrayList<>());
            list.add(u);

            graph.put(v, list);
        }

        go(0, -1, nums);
        buildSubTree(0, -1);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int t1, t2, t3;
                if (subTree.get(i).contains(j)) {
                    t1 = cache[0] ^ cache[i];
                    t2 = cache[j];
                    t3 = cache[0] ^ t1 ^ t2;
                } else if (subTree.get(j).contains(i)) {
                    t1 = cache[i];
                    t2 = cache[0] ^ cache[j];
                    t3 = cache[0] ^ t1 ^ t2;
                } else {
                    t1 = cache[j];
                    t2 = cache[i];
                    t3 = cache[0] ^ t1 ^ t2;
                }
                int max_t = Math.max(Math.max(t1, t2), t3);
                int min_t = Math.min(Math.min(t1, t2), t3);
                min = Math.min(min, max_t - min_t);
            }
        }

        return min;
    }

    Set<Integer> buildSubTree(int index, int p_index) {
        Set<Integer> result = new HashSet<>();
        result.add(index);
        for (int node : graph.getOrDefault(index, new ArrayList<>())) {
            if (p_index != node) {
                Set<Integer> childSet = buildSubTree(node, index);
                result.addAll(childSet);
            }
        }

        subTree.put(index, result);
        return result;
    }
    int[] cache;

    int go(int index, int p_index, int[] nums) {
        cache[index] = nums[index];
        for (int node : graph.getOrDefault(index, new ArrayList<>())) {
            if (p_index != node) {
                cache[index] = cache[index] ^ go(node, index, nums);
            }
        }

        return cache[index];
    }
}
