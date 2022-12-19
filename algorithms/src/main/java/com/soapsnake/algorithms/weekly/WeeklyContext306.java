package com.soapsnake.algorithms.weekly;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-08-14
 * JavaRoad
 */
public class WeeklyContext306 {

    public static void main(String[] args) {
        WeeklyContext306 weeklyContext306 = new WeeklyContext306();
        System.out.println(weeklyContext306);
        int[] num = {};
        //que.add(Long.MAX_VALUE);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        System.out.println(map.get(1));
        System.out.println(map.get(null));

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        List<Integer> list2= Collections.unmodifiableList(list);
        List<Integer> list3 = ImmutableList.copyOf(list);
        list.add(4);
        System.out.println(list2);
        System.out.println(list3);

        list.removeIf(a -> a == 3);

        System.out.println(list);
    }


    public int[][] largestLocal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int M = m - 2;
        int N = n - 2;
        int[][] res = new int[M][N];
        for (int i = 0; i < m - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                int max = Integer.MIN_VALUE;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        max = Math.max(max, grid[i + k][j + l]);
                    }
                }
                res[i][j] = max;
            }
        }
        return res;
    }

    public int edgeScore(int[] edges) {
        Queue<long[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return Long.compare(a[1], b[1]);
            } else {
                return Long.compare(a[0], b[0]);
            }
        });
        int n = edges.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int from = i;
            int to = edges[i];
            map.computeIfAbsent(to, x -> new ArrayList<>()).add(from);
        }
        System.out.println(map);
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(i, new ArrayList<>());
            long sum = 0;
            if (list.size() > 0) {
                sum += list.stream().mapToLong(Integer::longValue).sum();
            }
            queue.offer(new long[]{sum, i});
        }

        return (int) queue.peek()[0];
    }

    public String smallestNumber(String pattern) {
        int n = pattern.length();
        int m = n + 1;
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= m; i++) {
            que.offer(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (pattern.charAt(i) == 'I') {
                sb.append(que.poll());
            } else {
                int j = i;
                StringBuilder sb1 = new StringBuilder();
                //        String s = "IIIDIDDD";
                while (j < pattern.length() && pattern.charAt(j) == 'D') {
                    sb1.append(que.poll());
                    j++;
                }
                sb.append(sb1.reverse());
                i = j;
            }
        }
        return sb.toString();
    }


    public String minWindow(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return "";
        }
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        String result = "";

        while (right < s1.length()) {
            int tIndex = 0;
            while (right < s1.length()) {
                if (s1.charAt(right) == s2.charAt(tIndex)) {
                    tIndex++;
                }
                if (tIndex == s2.length()) {
                    break;
                }
                right++;
            }

            if (right == s1.length()) {
                break;
            }
            int left = right;
            tIndex = s2.length() - 1;
            while (left >= 0) {
                if (s1.charAt(left) == s2.charAt(tIndex)) {
                    tIndex--;
                }
                if (tIndex < 0) {
                    break;
                }
                left--;
            }
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                result = s1.substring(left, right + 1);
            }
            right = left + 1;
        }
        return result;
    }

    public int countNumbersWithUniqueDigits(int n) {
        int res = 10;
        int uniq = 9;
        int available = 9;
        while (n-- > 0 && available > 0) {
            uniq = uniq * available;
            res += uniq;
            available--;
        }
        return res;
    }

    public int countSpecialNumbers(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = n + 1; i > 0; i /= 10) {
            list.add(0, i % 10);
        }

        int res = 0, k = list.size();
        for (int i = 1; i < k; ++i) {
            res += 9 * count(9, i - 1);
        }
        Set<Integer> visit = new HashSet<>();
        for (int i = 0; i < k; ++i) {
            for (int j = i > 0 ? 0 : 1; j < list.get(i); ++j)
                if (!visit.contains(j)) {
                    res += count(9 - i, k - i - 1);
                }
            if (visit.contains(list.get(i))) break;
            visit.add(list.get(i));
        }
        return res;
    }


    public int count(int m, int n) {
        return n == 0 ? 1 : count(m, n - 1) * (m - n + 1);
    }




    public int numDupDigitsAtMostN(int n) {
        //总数 - 不重复的 = 重复的
        return n - dp(n);
    }

    //找不重复的数字
    public int dp(int n) {
        int total = 0;
        int[] visit = new int[10];
        List<Integer> digits = new ArrayList<>();
        while (n > 0) {
            digits.add(n % 10);
            n = n / 10;
        }
        int k = digits.size();
        for (int i = k - 1; i >= 1; i--) {
            total += 9 * A(9, i-1);
        }
        for (int i=k-1;i>=0;i--){
            int num = digits.get(i);
            for (int j=(i==k-1?1:0);j<num;j++){
                if (visit[j] != 0){
                    continue;
                }
                total+=A(10-(k-i),i);
            }
            visit[num]++;
            if (visit[num] >1){
                break;
            }
            if (i==0){
                total = total+1;
            }
        }
        return total;
    }
    public int fact(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * fact(n - 1);
    }

    public int A(int m, int n) {
        return fact(m) / fact(m - n);
    }
}
