package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-03-18
 * JavaRoad
 */
public class Test {


    public static Map<Integer, Integer> dijkastra(Map<Integer, List<Integer>> map, int src) {
        PriorityQueue<int[]> que = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        Map<Integer, Integer> res = new HashMap<>();
        res.put(src, 0);
        que.offer(new int[]{src, 0});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            if (res.containsKey(cur[0])) continue;
            res.put(cur[0], cur[1]);
            for (Integer next : map.get(cur[0])) {
                if (!res.containsKey(next)) {
                    que.offer(new int[]{next, cur[1] + 1});
                }
            }
        }
        return res;
    }

    //求一个字符串中最大重复子串的长度
    public int longestRepeatingSubstring(String S) {
        int res = Integer.MIN_VALUE;
        TrieNode root = new TrieNode();
        for (int i = 0; i < S.length(); i++) {
            TrieNode cur = root;
            for (int j = i; j < S.length(); j++) {
                int index = S.charAt(j);
                if (cur.next[index] != null) {
                    S.substring(1, 2);
                    res = Math.max(res, j - i + 1);
                } else {
                    cur.next[index] = new TrieNode();
                }
                cur = cur.next[index];
            }
        }
        return res;
    }

    public String longestDupSubstring(String s) {
        String res = "";
        int max = -1;
        TrieNode root = new TrieNode();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            TrieNode cur = root;
            for (int j = i; j < n; j++) {
                int index = s.charAt(j) - 'a';
                if (cur.next[index] != null) {
                    int len = j - i + 1;
                    if (len > max) {
                        res = s.substring(i - 1, len);
                        max = len;
                    }
                } else {
                    cur.next[index] = new TrieNode();
                }
                cur = cur.next[index];
            }
        }
        return res;
    }

    private static final long q = (1 << 31) - 1;
    private static final long R = 256;
    public String longestDupSubstring2(String S) {
        int l = 1;
        int r = S.length() - 1;
        int start = 0;
        int maxLen = 0;

        while (l <= r) {
            int len = l + (r - l) / 2;
            boolean found = false;

            Map<Long, List<Integer>> map = new HashMap<>();
            long hash = hash(S, len);
            map.put(hash, new ArrayList<>());
            map.get(hash).add(0);
            long RM = 1L;
            for (int i = 1; i < len; i++) RM = (R * RM) % q;
            loop:
            for (int i = 1; i + len <= S.length(); i++) {
                hash = (hash + q - RM * S.charAt(i - 1) % q) % q;
                hash = (hash * R + S.charAt(i + len - 1)) % q;
                if (!map.containsKey(hash)) {
                    map.put(hash, new ArrayList<>());
                } else {
                    for (int j : map.get(hash)) {
                        if (compare(S, i, j, len)) {
                            found = true;
                            start = i;
                            maxLen = len;
                            break loop;
                        }
                    }
                }
                map.get(hash).add(i);
            }
            if (found) l = len + 1;
            else r = len - 1;
        }
        return S.substring(start, start + maxLen);
    }

    public static boolean compare(String S, int i, int j, int len) {
        for (int count = 0; count < len; count++) {
            if (S.charAt(i++) != S.charAt(j++)) return false;
        }
        return true;
    }

    public static long hash(String s, int len) {
        long h = 0;
        for (int j = 0; j < len; j++) {
            h = (R * h + s.charAt(j)) % q;
        }
        return h;
    }

    public static void main(String[] args) throws Exception {
        Test t = new Test();
        String s = "aa";
        System.out.println(t.longestDupSubstring2(s));
    }

    static class TrieNode {
        TrieNode[] next;
        public TrieNode() {
            this.next = new TrieNode[26];
        }

        @Override
        public String toString() {
            return "TrieNode{" +
                    "next=" + Arrays.toString(next) +
                    '}';
        }
    }
}
