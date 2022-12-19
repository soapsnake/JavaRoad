package com.soapsnake.algorithms.weekly;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-08-21
 * JavaRoad
 */
public class WeeklyContext307 {


    Long maxNum;
    Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) {
        WeeklyContext307 weeklyContext307 = new WeeklyContext307();

        //System.out.println(weeklyContext307);
        String num = "444947137";
        //System.out.println(weeklyContext307.largestPalindromic(num));
        int initEng = 5;
        int initExp = 3;
        int[] eng = {1, 4, 3, 2};
        int[] exp = {2, 6, 3, 1};
        System.out.println(weeklyContext307.minNumberOfHours(initEng, initExp, eng, exp));  //should 8


        String words = "123";
        StringBuilder sb = new StringBuilder(words);
        sb.deleteCharAt(1);
    }

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        //initialExperience + xunlian + expier[i]
        //energy + xunlian - energy[i]
        int sumEnergy = 0;
        int needEng = 0;
        for (int i = 0; i < energy.length; i++) {
            sumEnergy += energy[i];
            if (initialEnergy <= energy[i]) {
                int curNeed = energy[i] - initialEnergy + 1;
                needEng += curNeed;
                initialEnergy += curNeed;
            }
            initialEnergy -= energy[i];
        }
        int totalExp = 0;
        int maxExp = 0;
        int needExp = 0;
        for (int i = 0; i < experience.length; i++) {
            if (initialExperience <= experience[i]) {
                int curNeed = experience[i] - initialExperience + 1;
                needExp += curNeed;
                initialExperience += curNeed;
            }
            initialExperience += experience[i];
        }
        System.out.println("fdsafad" + needEng);
        System.out.println("fdafda" + needExp);
        return needEng + needExp;
    }

    public String largestPalindromic(String num) {
        longestPalindrome(num);
        return String.valueOf(maxNum);
    }

    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                //发现新的最长回文串
                start = i - (len - 1) / 2;
                end = i + len / 2;
                String tmp = s.substring(start, end + 1);
                System.out.println(tmp);
                Long num = Long.parseLong(tmp);
                maxNum = Math.max(num, maxNum);
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public int amountOfTime(TreeNode root, int start) {
        //读题: 从start节点开始,其实就是无向图,每一次bfs,计为1
        dfs(root);
        boolean[] visit = new boolean[10010];
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        visit[start] = true;
        int stp = 0;
        while (!que.isEmpty()) {
            int curSize = que.size();
            for (int i = 0; i < curSize; i++) {
                int cur = que.poll();
                for (int next : graph.getOrDefault(cur, new ArrayList<>())) {
                    if (!visit[next]) {
                        visit[next] = true;
                        que.offer(next);
                    }
                }
            }
            stp++;
        }
        return stp;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode a = root;
        if (root.left != null) {
            graph.computeIfAbsent(a.val, x -> new ArrayList<>()).add(root.left.val);
            graph.computeIfAbsent(root.left.val, x -> new ArrayList<>()).add(a.val);
        }
        if (root.right != null) {
            graph.computeIfAbsent(a.val, x -> new ArrayList<>()).add(root.right.val);
            graph.computeIfAbsent(root.right.val, x -> new ArrayList<>()).add(a.val);
        }
        dfs(root.left);
        dfs(root.right);
    }


    public int makeStringSorted(String s) {
        init(s.length());
        int[] data = new int[26];
        for (int i = 0; i < s.length(); i++) {
            data[s.charAt(i) - 'a']++;
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int mid = 0;
            for (char j = 'a'; j < s.charAt(i); j++) {
                if (data[j - 'a'] == 0) continue;
                data[j - 'a']--;
                mid = (mid + cal(data)) % mod;
                data[j - 'a']++;
            }
            res = (res + mid) % mod;
            data[s.charAt(i) - 'a']--;
        }
        return res;
    }

    private int[][] cmn = null;
    private final int mod = (int) (1e9 + 7);
    public void init(int len) {
        cmn = new int[len + 1][len + 1];
        cmn[0][0] = 1;
        for (int i = 1; i < cmn.length; i++) {
            cmn[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                cmn[i][j] = (cmn[i - 1][j - 1] + cmn[i - 1][j]) % mod;
            }
        }
    }

    public int cal(int[] data) {
        ArrayList<Integer> ss = new ArrayList<>();
        for (Integer item : data) {
            if (item != 0) ss.add(item);
        }
        if (ss.size() == 1) return 1;
        int sum1 = ss.get(0) + ss.get(1);
        int sum2 = ss.get(0);
        long res = cmn[sum1][sum2];
        for (int i = 2; i < ss.size(); i++) {
            sum1 += ss.get(i);
            sum2 += ss.get(i - 1);
            res = (res * cmn[sum1][sum2]) % mod;
        }
        return (int) res;
    }
}


