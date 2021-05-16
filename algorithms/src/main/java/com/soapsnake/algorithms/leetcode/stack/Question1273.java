package com.soapsnake.algorithms.leetcode.stack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;


/**
 * 
 * Created on 2021-04-17
 */
public class Question1273 {

    public String removeDuplicates(String s, int k) {
        Stack<Pair> stk = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stk.isEmpty() || c != stk.peek().getKey()) {
                stk.push(new Pair(c, 1));
            }else {
                int cnt = stk.pop().getValue() + 1;
                if (cnt != k) stk.push(new Pair(c, cnt));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            int cnt = stk.peek().getValue();
            char c = stk.pop().getKey();
            while (cnt-- > 0) { sb.append(c); }
        }
        return sb.reverse().toString();
    }


    class Pair {
        private char a;
        private int cnt;

        public Pair(char c, int i) {
            this.a = c;
            this.cnt = i;
        }

        public char getKey() {
            return this.a;
        }

        public int getValue() {
            return this.cnt;
        }
    }


    public static List<Integer> multiple(int x, int y, int z, int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if ((i % x == 0 || i % y == 0) && i % z != 0) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(multiple(3, 5, 10, 12));
    }


    public static List<Integer> misss(List<List<Integer>> firstReservationList,
            List<List<Integer>> secondReservationList) {

        List<Integer> first = firstReservationList.stream().map(list -> list.get(0)).collect(Collectors.toList());
        List<Integer> second = secondReservationList.stream().map(List -> List.get(0)).collect(Collectors.toList());
        List<Integer> secondCopy = new ArrayList<>(second);
        secondCopy.retainAll(first);
        second.removeAll(secondCopy);
        return secondReservationList.stream().filter(ids -> second.contains(ids.get(0)))
                .sorted(Comparator.comparingInt(o -> o.get(1))).map(list -> list.get(0)).collect(Collectors.toList());





    }


    public static List<List<Integer>> ep(List<List<Integer>> employeeCalls, int k) {
        List<List<Integer>> res = new ArrayList<>();

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (List<Integer> list : employeeCalls) {
            if (map.containsKey(list.get(0))) {
                List<int[]> temp = map.get(list.get(0));
                int start = list.get(1);
                int end = list.get(2);
                int[] last = temp.get(temp.size() - 1);
                if (last[1] < start) {
                    int[] times = new int[] {list.get(1), list.get(2)};
                    temp.add(times);
                }
            } else {
                List<int[]> temp = new ArrayList<>();
                int[] times = new int[] {list.get(1), list.get(2)};
                temp.add(times);
                map.put(list.get(0), temp);
            }
        }
        return res;
    }

}
