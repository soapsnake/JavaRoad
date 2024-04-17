package com.soapsnake.algorithms.weekly;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-11-20
 * JavaRoad
 */
public class WeeklyContext320 {

    public static void main(String[] args) {
        WeeklyContext320 w = new WeeklyContext320();
        System.out.println();

        int[][] roads = {{0,1},{0,2},{3,2},{0,4},{1,5},{5,6},{3,7}};
                int seats = 1;
        System.out.println(w.minimumFuelCost(roads, seats));   //should 13
    }


    public int unequalTriplets(int[] nums) {
        //全是正数,找三个数,要全部不一样,
        int n = nums.length;
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] == nums[i]) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    if (nums[k] == nums[j] ) {
                        continue;
                    }
                    res++;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        this.travers(root);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {
            int t = queries.get(i);
            List<Integer> cur = new ArrayList<>();
            cur.add(map.floorKey(t) == null ? -1 : map.floorKey(t));
            cur.add(map.ceilingKey(t) == null ? -1 : map.ceilingKey(t));
            res.add(cur);
        }
        return res;
    }
    TreeMap<Integer, Integer> map = new TreeMap<>();
    private void travers(TreeNode root) {
        if (root == null) {
            return;
        }
        map.put(root.val, null);
        travers(root.left);
        travers(root.right);
    }


    public long minimumFuelCost(int[][] roads, int seats) {
        //1.无向图, 2. 所有点都要到0号点,路径为总油耗,
        //如果 到0总路径长度 >= seats, 就可以合并路径上所有点的开支, 如果 < seats, 那么 zong - seats = extra, cur = zong + zong - seats
        //与0直接相邻的点,决定了总路径个数
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int total = 0;
        for (int[] r : roads) {
            int a = r[0];
            int b = r[1];
            graph.computeIfAbsent(a, x -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, x -> new ArrayList<>()).add(a);
        }
        List<Integer> heads =  new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (entry.getKey() == 0) {
                heads = entry.getValue();
            }
        }
        System.out.println(graph);
        System.out.println(heads);
        Set<Integer> set = new HashSet<>();
        for (int head : heads) {
            int path = 0;
            Queue<Integer> que = new LinkedList<>();
            que.offer(head);
            while (!que.isEmpty()) {
                int cur = que.poll();
                System.out.println("cur = " + cur + " head = " + head);
                if (set.contains(cur)) continue;
                set.add(cur);
                path++;
                for (int next : graph.getOrDefault(cur, new ArrayList<>())) {
                    if (!set.contains(next) && next != 0) {
                        que.offer(next);
                    }
                }
            }
            total +=(int)Math.ceil((double) path/seats);
        }
        return total;
    }
}
