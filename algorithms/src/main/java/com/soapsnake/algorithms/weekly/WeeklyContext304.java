package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-07-31
 * JavaRoad
 */
public class WeeklyContext304 {

    public static void main(String[] args) {
        WeeklyContext304 weeklyContext304 = new WeeklyContext304();
        int[] grages = {2, 31, 41, 31, 36, 46, 33, 45, 47, 8, 31, 6, 12, 12, 15, 35};
        int[] grages1 = {8, 8};
        int[] grages2 = {10, 6, 12, 7, 3, 5};
        //System.out.println(weeklyContext304.maximumGroups(grages));  //should 5

        String test = "babad";
        System.out.println(test.substring(0, 3));  //[0, 3) -> bab
        int[] edges = {5, 3, 1, 0, 2, 4, 5};
        int ndoe1 = 3;
        int node2 = 2;
        //System.out.println(weeklyContext304.closestMeetingNode(edges, ndoe1, node2));  //should 3
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        //读题:1.有向图 2.每个点最多一条出边
        //edges可能有环
        //求得是node1和node2都能到的节点里边,距离最小的点,
        Map<Integer, List<int[]>> graph = new HashMap<>();
        int n = edges.length;
        for (int i = 0; i < n; i++) {
            if (edges[i] == -1) {
                continue;
            }
            int a = i;
            int b = edges[i];
            graph.computeIfAbsent(a, x -> new ArrayList<>()).add(new int[]{b, 1});
        }
        System.out.println(graph);
        Map<Integer, Integer> res1 = dijakstra(graph, node1);
        Map<Integer, Integer> res2 = dijakstra(graph, node2);
        System.out.println("res1 =" + res1);
        System.out.println("res2 = " + res2);
        int minDis = Integer.MAX_VALUE;
        int minNode = -1;
        for (int i = 0; i < n; i++) {
            int dist1 = res1.getOrDefault(i, Integer.MAX_VALUE);
            int dist2 = res2.getOrDefault(i, Integer.MAX_VALUE);
            if (dist1 != Integer.MAX_VALUE && dist2 != Integer.MAX_VALUE) {
                int min = Math.max(dist2, dist1);
                if (min < minDis) {
                    System.out.println("min =" + min + " minNode = " + i);
                    minDis = min;
                    minNode = i;
                }
            }
        }
        return minNode;
    }

    public long minSumSquareDiff2(int[] nums1, int[] nums2, int k1, int k2) {
        long minSumSquare = 0;
        int[] diffs = new int[100_001];
        long totalDiff = 0;
        long kSum = k1 + k2;
        int currentDiff;
        int maxDiff = 0;
        for (int i = 0; i < nums1.length; i++) {
            // get current diff.
            currentDiff = Math.abs(nums1[i] - nums2[i]);
            // if current diff > 0, count/store it. If not,then ignore it.
            if (currentDiff > 0) {
                totalDiff += currentDiff;
                diffs[currentDiff]++;
                maxDiff = Math.max(maxDiff, currentDiff);
            }
        }
        // if kSum (k1 + k2) < totalDifferences, it means we can make all numbers/differences 0s
        if (totalDiff <= kSum) {
            return 0;
        }
        // starting from the back, from the highest difference, lower that group one by one to the previous group.
        // we need to make all n diffs to n-1, then n-2, as long as kSum allows it.
        for (int i = maxDiff; i> 0 && kSum > 0; i--) {
            if (diffs[i] > 0) {
                // if current group has more differences than the totalK, we can only move k of them to the lower level.
                if (diffs[i] >= kSum) {
                    diffs[i] -= kSum;
                    diffs[i-1] += kSum;
                    kSum = 0;
                } else {
                    // else, we can make this whole group one level lower.
                    diffs[i-1] += diffs[i];
                    kSum -= diffs[i];
                    diffs[i] = 0;
                }
            }
        }

        for (int i = 0; i <= maxDiff; i++) {
            if (diffs[i] > 0) {
                minSumSquare += (long) (Math.pow((long)i, 2)) * diffs[i];
            }
        }
        return minSumSquare;
    }

    public long minSumSquareDiff3(int[] nums1, int[] nums2, int k1, int k2) {
        //读题: nums1任意数字可以+1 k1次,nums2可以k2次,要求变化后的差值平方总和最小.
        int n = nums1.length;
        long[] diff = new long[100001];
        long totalDiff = 0;
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            int curDiff = Math.abs(nums1[i] - nums2[i]);
            totalDiff += curDiff;
            if (curDiff > 0) {
                diff[curDiff]++;
            }
            maxDiff = Math.max(curDiff, maxDiff);
        }
        int kSum = k1 + k2;
        if (kSum >= totalDiff) {
            return 0;
        }
        for (int i = maxDiff; i > 0 && kSum > 0; i--) {
            if (diff[i] > 0) {
                if (kSum <= diff[i]) {
                    diff[i] -= kSum;
                    diff[i - 1] += kSum;
                    kSum = 0;
                } else {
                    diff[i - 1] += diff[i];
                    kSum -= diff[i];
                    diff[i] = 0;
                }
            }
        }

        long total = 0;
        for (int i = 0; i <= maxDiff; i++) {
            if (diff[i] > 0) {
                total += (long)Math.pow((long)i, 2) * diff[i];
            }
        }
        return total;
    }

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        //读题: nums1任意数字可以+1 k1次,nums2可以k2次,要求变化后的差值平方总和最小.
        int n = nums1.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
        }
        System.out.println(Arrays.toString(diff));
        int remain = k1 + k2;
        for (int i = 0; i < n; i++) {
            if (remain != 0) {
                remain -= diff[i];
                if (remain < 0) {
                    diff[i] = Math.abs(remain);
                    remain = 0;
                } else {
                    diff[i] = 0;
                }
            }
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += diff[i] * diff[i];
        }
        return total;
    }

    public int longestCycle(int[] edges) {
        int res = -1;
        boolean[] vis = new boolean[edges.length]; // global visisted
        //遍历所有的点
        for (int i = 0; i < edges.length; i++) {
            //如果点i之前访问过,那么跳过
            if (vis[i]) continue;

            // node -> path length;
            HashMap<Integer, Integer> x = new HashMap<>();  // local visited

            for (int idx = i, dist = 0; idx != -1; idx = edges[idx]) {
                //以当前点为起点进行遍历,因为每一个点都只会有一条出边
                if (x.containsKey(idx)) {
                    //如果以i为起点,访问到的某个点是二次访问,那就说明遇到了环路,map既可以用来探测环路,也能用来记录遍历的长度
                    res = Math.max(res, dist - x.get(idx));
                    //只会有一个环路,为啥了?
                    break;
                }
                if (vis[idx]) break;
                vis[idx] = true;
                x.put(idx, dist++);
            }
        }
        return res;
    }

    private static Map<Integer, Integer> dijakstra(Map<Integer, List<int[]>> graph, int src) {
        Queue<int[]> que = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        que.offer(new int[]{src, 0});
        Map<Integer, Integer> res = new HashMap<>();
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            if (res.containsKey(cur[0])) continue;  //这个为啥是必须的了?
            res.put(cur[0], cur[1]);
            for (int[] next : graph.getOrDefault(cur[0], new ArrayList<>())) {
                if (res.containsKey(next[0])) continue;
                que.offer(new int[]{next[0], next[1] + cur[1]});
            }
        }
        return res;
    }

    public int maximumGroups(int[] grades) {
        //   1.全是正数 2. 每一个组要比后面的组人少, 3.每一个组sum要比后面的组sum小
        Arrays.sort(grades);
        int n = grades.length;
        int count = 1;
        int curSize = 2;
        int prevSum = grades[0];
        System.out.println(Arrays.toString(grades));
        for (int i = 1; i < n; ) {
            System.out.println("i = " + i + " curSize = " + curSize);
            int curSum = 0;
            if (curSize + i > n) break;
            for (int j = i; j < curSize + i; j++) {
                curSum += grades[j];
            }
            System.out.println("prev = " + prevSum + " curSum = " + curSum);
            if (curSum > prevSum) {
                count++;
                prevSum = curSum;
                i += curSize;
                curSize++;
            } else {
                break;
            }
        }
        return count;
    }
}
