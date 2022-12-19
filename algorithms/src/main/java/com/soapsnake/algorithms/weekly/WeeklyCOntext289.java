package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-04-17
 * JavaRoad
 */
public class WeeklyCOntext289 {

    public String digitSum(String s, int k) {
        if (s.length() < k) return s;

        while (s.length() > k) {
            StringBuilder tmp = new StringBuilder();
            int i = 0;
            for (; i + k < s.length(); i+= k) {
                String str = s.substring(i, i + k);
                //System.out.println("i=" + i + " str = " + str);
                int sum = cacu(str);
                tmp.append(sum);
            }
            tmp.append(cacu(s.substring(i)));
            //System.out.println(tmp);
            s = tmp.toString();
        }
        return s;
    }

    private int cacu(String str) {
        char[] chars = str.toCharArray();
        int t = 0;
        for (int i = 0; i < chars.length; i++) {
            t += chars[i] - '0';
        }
        return t;
    }

    public static void main(String[] args) {
        WeeklyCOntext289 weeklyCOntext289 = new WeeklyCOntext289();
        //String s = "11111222223";
        int k = 3;


        

        //System.out.println(weeklyCOntext289.digitSum(s, k));
        //int[] tasks = {69,65,62,64,70,68,69,67,60,65,69,62,65,65,61,66,68,61,65,63,60,66,68,66,67,65,63,65,70,69,70,62,68,70,60,68,65,61,64,65,63,62,62,62,67,62,62,61,66,69};
        //System.out.println(weeklyCOntext289.minimumRounds(tasks));


        //System.out.println(calculateRound(8));

        //int[] p = {-1};
        //String s  = "z";
        //System.out.println(weeklyCOntext289.longestPath(p, s));
    }


    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : tasks) {
            count.put(num, count.getOrDefault(num,0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> node: count.entrySet()) {
            int num = node.getKey();
            int cishu = node.getValue();
            int round = calculateRound(cishu);
            System.out.println("num = " + num + " cishu = " + cishu + " round = " + round);

            if (round == -1) return -1;
            res += round;
        }
        return res;
    }

    private static int calculateRound(int k) {
        if (k < 2) return -1;
        int count = 0;
        if (k % 3 == 0) {
            return k / 3;
        }
        if (k == 4) return 2;
        while (k >= 0) {
            System.out.println("k = " + k);
            k -= 3;
            count++;
        }
        if (k % 2 == 0) count += k / 2;
        return count;
    }

    public int longestPath(int[] parent, String s) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> from = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        Collections.sort(list);
        if (parent.length == 1) return 1;
        for (int i = 0; i < parent.length; i++) {
            int a = parent[i];
            if (a == -1) continue;
            int b = i;
            graph.computeIfAbsent(a, x -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, x -> new ArrayList<>()).add(a);
        }

        from =  graph.keySet();
        int res = -1;
        for (int n : from) {
            int route = this.calculateRoute(graph, n, s);
            res = Math.max(res, route);
        }
        return res;
    }

    private int calculateRoute(Map<Integer, List<Integer>> graph, int n, String s) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(n);
        Set<Character> tmp = new HashSet<>();
        System.out.println("n = " + n + " graph = " + graph);
        int count = 0;
        while (!que.isEmpty()) {
            int cur = que.poll();
            char curchar = s.charAt(cur);
            System.out.println("curchar = " + curchar);
            if (!tmp.add(curchar)) continue;
            count++;
            for (int next: graph.get(cur)) {
                if (!tmp.contains(s.charAt(next))) {
                    que.offer(next);
                }
            }
        }
        return count;
    }


}
