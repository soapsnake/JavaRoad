package com.soapsnake.algorithms.cruel;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.sound.sampled.Line;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
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
 * Created on 2022-08-07
 * JavaRoad
 */
public class WeeklyContext305 {


    public static void main(String[] args) {
        WeeklyContext305 weeklyContext305 = new WeeklyContext305();
        int[] nums = {1, 1, 2, 3, 7, 7, 7, 7, 7, 7, 7, 7, 7, 10};
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            treeMap.put(nums[i], i);
        }
        System.out.println(binerSearch(nums, 8));
        System.out.println(treeMap.ceilingEntry(8).getValue());
        System.out.println(treeMap.floorEntry(8).getValue());

        //System.out.println(weeklyContext305.validPartition(nums));
    }

    private int treeBinerSearch(int[] nums, int k) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            treeMap.put(nums[i], i);
        }
        //返回nums中第一个>=k的数的索引
        return treeMap.ceilingEntry(k).getValue();
        //返回nums中第一个<=k的数的索引
        //return treeMap.floorEntry(k).getValue();
    }

    private static int binerSearch(int[] nums, int i) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < i) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public int arithmeticTriplets(int[] nums, int diff) {
        int n = nums.length;
        int count = 0;
        Set<String> set = new HashSet<>();
        for (int i = 1; i < n; i++) {
            int left = Arrays.binarySearch(nums, 0, i + 1, nums[i] - diff);
            int right = Arrays.binarySearch(nums, i, n, nums[i] + diff);
            if (left >= 0 && right >= 0) {
                set.add(left + "" + i + "" + right);
            }
        }
        System.out.println(set);
        return set.size();
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            graph.computeIfAbsent(a, x -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, x -> new ArrayList<>()).add(a);
        }
        Set<Integer> set = new HashSet<>();
        for (int num : restricted) {
            set.add(num);
        }
        Queue<Integer> que = new LinkedList<>();
        que.offer(0);
        boolean[] visit = new boolean[n];
        int res = 1;
        while (!que.isEmpty()) {
            int cur = que.poll();
            res++;
            visit[cur] = true;
            if (set.contains(cur)) continue;
            for (int next : graph.getOrDefault(cur, new ArrayList<>())) {
                if (!visit[next] && !set.contains(next)) {
                    que.offer(next);
                }
            }
        }
        return res;
    }


    public boolean validPartition(int[] nums) {
        //划分的数组必须连续,
        //子数组只要满足3个条件中的一个就行: 1.2个相等, 2.3个相等, 3. 3个递增1
        //感觉像是DP问题
        int n = nums.length;
        Boolean[] memo = new Boolean[n];
        return dfs(nums, 0, memo);
    }

    private boolean dfs(int[] nums, int i, Boolean[] memo) {
        //System.out.println("i = " + i + " t =" + t);
        if (i >= nums.length) return true;
        if (memo[i] != null) {
            return memo[i];   //从0 -> i已经计算过结果了
        }
        int[] len = {2, 3};
        boolean res = false;
        for (int len1 : len) {
            if (valid(nums, i, len1)) {
                if (dfs(nums, i + len1, memo)) {
                    res = true;
                }
            }
        }
        return memo[i] = res;
    }

    private boolean valid(int[] nums, int from, int len1) {
        if (len1 == 2 && from + 1 < nums.length) {
            int num1 = nums[from];
            int num2 = nums[from + 1];
            return num1 == num2;
        } else if (len1 == 3 && from + 2 < nums.length) {
            int num1 = nums[from];
            int num2 = nums[from + 1];
            int num3 = nums[from + 2];
            return (num1 == num2 && num2 == num3) || (num2 - num1 == 1 && num3 - num2 == 1);
        }
        return false;
    }

    public int longestIdealString(String s, int k) {
        //读题: 求s的最长不连续子序列, 要求新子序列的连续两个字符asc差值不大于k
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) - s.charAt(i) <= k) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int res = -1;
        for (int i = 0; i < n; i++) {
            res = Math.max(dp[i], res);
        }
        return res;
    }

    public int longestIdealString2(String s, int k) {
        //读题: 求s的最长不连续子序列, 要求新子序列的连续两个字符asc差值不大于k
        //思路: 用map保存已每个字符为结尾的最长字符串长度
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = -1;
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            for (int j = 0; j <= k; j++) {
                char c1 = (char) (cur + j);
                char c2 = (char) (cur - j);
                if (map.containsKey(c1)) {
                    dp[i] = Math.max(dp[i], map.get(c1) + 1);
                }
                if (map.containsKey(c2)) {
                    dp[i] = Math.max(dp[i], map.get(c2) + 1);
                }
                map.put(cur, dp[i]);
                res = Math.max(dp[i], res);
            }
        }
        return res;
    }


    public int longestCycle(int[] edges) {
        //读题: 有向图的最长环路长度;
        //思路: 对有向图进行拓扑排序,所有入度可以减到0的点,都不可能是环路的点,先剔除,然后依次从环路点出发探测,记录最长路径
        int n = edges.length;
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            int to = edges[i];
            if (to == -1) {
                continue;
            }
            inDegree[to]++;
        }
        boolean[] notCycle = new boolean[n];
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                que.offer(i);
                notCycle[i] = true;
            }
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (edges[cur] == -1) continue;
            --inDegree[edges[cur]];
            if (inDegree[edges[cur]] == 0) {
                notCycle[edges[cur]] = true;
                que.offer(edges[cur]);
            }
        }
        int res = -1;
        for (int i = 0; i < notCycle.length; i++) {
            if (notCycle[i]) continue;
            inDegree[i] = 0;
            que.offer(i);
            int cnt = 1;
            while (!que.isEmpty()) {
                int cur = que.poll();
                notCycle[cur] = true;
                cnt++;
                if (edges[cur] == -1) continue;
                int next = edges[cur];
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    que.offer(next);
                }
            }
            res = Math.max(res, cnt);
        }
        return res;
    }

    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] inDegree = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            inDegree[favorite[i]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer v = queue.poll();
            int w = favorite[v];
            inDegree[w]--;
            if (inDegree[w] == 0) {
                queue.offer(w);
            }
            dp[w] = Math.max(dp[w], dp[v] + 1);
        }
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] <= 0) {
                continue;
            }
            int t = i, num = 0, way = 0;
            while (inDegree[t] != -1) {
                inDegree[t] = -1;
                way += dp[t];
                t = favorite[t];
                num++;
            }
            if (num == 2) {
                b += way;
            } else {
                a = Math.max(a, num);
            }
        }
        return Math.max(a, b);
    }

    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        ArrayList<Node>[] heads = new ArrayList[26];
        for (int i = 0; i < 26; ++i) {
            heads[i] = new ArrayList<>();
        }

        for (String word : words) {
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));
        }

        for (char c : S.toCharArray()) {
            ArrayList<Node> old_bucket = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<Node>();

            for (Node node : old_bucket) {
                node.index++;
                if (node.index == node.word.length()) {
                    ans++;
                } else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
            old_bucket.clear();
        }
        return ans;
    }

    class Node {
        String word;
        int index;

        public Node(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }

    public int numMatchingSubseq2(String s, String[] words) {
        Map<Character, List<Node>> map = new HashMap<>();
        for (String word : words) {
            map.computeIfAbsent(word.charAt(0), x -> new ArrayList<>()).add(new Node(word, 0));
        }
        int res = 0;
        for (int i = 0;i < s.length(); i++) {
            char cur = s.charAt(i);
            List<Node> old_list = map.getOrDefault(cur, new ArrayList<>());
            map.put(cur, new ArrayList<>());
            for (Node node : old_list) {
                ++node.index;
                if (node.index == node.word.length()) {
                    res++;
                    continue;
                }
                map.computeIfAbsent(node.word.charAt(node.index), x -> new ArrayList<>()).add(node);
            }
        }
        return res;
    }
}
