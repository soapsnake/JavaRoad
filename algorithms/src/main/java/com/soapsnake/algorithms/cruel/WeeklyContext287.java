package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.LongStream;

public class WeeklyContext287 {



    public static void main(String[] args) {
        WeeklyContext287 weeklyContext285 = new WeeklyContext287();
        String s1 = "00:00";
        String s2 = "23:59";
        //System.out.println(weeklyContext285.convertTime(s1, s2));   //32

        int[][] win = {{2,3},{1,3},{5,4},{6,4}};
        System.out.println(weeklyContext285.findWinners(win));
    }


    public int convertTime(String current, String correct) {
        String[] a = current.split(":");
        String[] b = correct.split(":");
        int h1 = parse(a[0]);
        int m1 = parse(a[1]);
        int h2 = parse(b[0]);
        int m2 = parse(b[1]);
        int hh = h2 - h1;
        if (hh < 0) hh += 24;
        int mm = m2 - m1;
        int total = hh * 60 + mm;
        System.out.println("total = " + total);
        int res = 0;
        res += total / 60;
        total %= 60;

        res += total / 15;
        total %= 15;
        res += total / 5;
        total %= 5;
        return res + total;
    }

    private int parse(String s) {
        if(s.charAt(0) == '0') {
            s = s.substring(1);
        }
        return Integer.parseInt(s);
    }

    public List<List<Integer>> findWinners(int[][] matches) {
        int n = 10010;
        int[] players = new int[n];
        Boolean[] lose = new Boolean[n];
        for(int[] match : matches) {
            int a = match[0];
            int b = match[1];
            players[b]++;
            lose[b] = true;
            if (lose[a] == null) lose[a] = false;
            //graph.computeIfAbsent(a, x -> new ArrayList<>()).add(b);
        }
        List<Integer> nolose = new ArrayList<>();
        List<Integer> loseone = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (players[i] == 1) {
                loseone.add(i);
            }
            if (lose[i] != null && !lose[i]) nolose.add(i);
        }
        nolose.sort(Integer::compareTo);
        loseone.sort(Integer::compareTo);
        List<List<Integer>> res = new ArrayList<>();
        res.add(nolose);
        res.add(loseone);
        return res;
    }

    public int maximumCandies(int[] candies, long k) {
        long sum = 0L;
        for (int num : candies) {
            sum += num;
        }
        long l = 0, r = sum, res = 0;
        while (l <= r) {
            long mid = l + (r - l) >> 1;
            if (check(candies, mid, k)) {
                res = mid;
                l  = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return (int) res;
    }

    public static boolean check(int[] candies, long value, long k) {
        for (int candy : candies) {
            if (candy < value) continue;
            else if (candy == value) k--;
            else k = k - (int) (candy / value);
        }
        return k <= 0;
    }

}

class Encrypter {
    Map<Character, String> map;
    Map<String, Integer> count;

    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        map = new HashMap<>();
        count = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        for (String s : dictionary) {
            String e = encrypt(s);
            count.put(s, count.getOrDefault(e, 0) + 1);
        }
    }

    public String encrypt(String word1) {
        /**
         * 对字符串中的每个字符 c ，先从 keys 中找出满足 keys[i] == c 的下标 i 。
         * 在字符串中，用 values[i] 替换字符 c 。
         */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word1.length(); i++) {
            sb.append(map.get(word1.charAt(i)));
        }
        return sb.toString();
    }

    public int decrypt(String word2) {
        return count.getOrDefault(word2, 0);
    }
}
