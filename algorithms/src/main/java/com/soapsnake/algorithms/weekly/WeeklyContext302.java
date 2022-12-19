package com.soapsnake.algorithms.weekly;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class WeeklyContext302 {

    public static void main(String[] args) throws Exception {
        WeeklyContext302 week = new WeeklyContext302();
        int[][] q = {{1,1},{2,3},{4,2},{1,2}};
        int[][] q1 = {{5,10}};

        System.out.println(Arrays.toString(week.smallestTrimmedNumbers(test4, q1))); //shoud 973
    }
     static int[] test2 = {18,43,36,13,7};
     static int[] test3 = {229,398,269,317,420,464,491,218,439,153,482,169,411,93,147,50,347,210,251,366,401};
    public int maximumSum(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;
        Map<Integer, Queue<Integer>> map = new HashMap<>(); // 数位和 -> 原始数字;
        for (int i = 0; i < n; i++) {
            int sum = count(nums[i]);
            if (map.containsKey(sum)) {
                Queue<Integer> que = map.get(sum);
                int top = que.peek();
                res = Math.max(res, top + nums[i]);
                que.offer(nums[i]);
            } else {
                Queue<Integer> que = new PriorityQueue<>((a, b) -> b - a);
                que.offer(nums[i]);
                map.put(sum, que);
            }
        }
        return res == Integer.MIN_VALUE ? -1 : res;
    }

    private static int count(int a ) {
        int res = 0;
        while (a != 0) {
            res += a % 10;
            a /= 10;
        }
        return res;
    }
    
    
    private static boolean deng(int a, int b) {
        int suma = 0;
        while (a != 0) {
            suma += a % 10;
            a /= 10;
        }
        int sumb = 0;
        while (b != 0) {
            sumb += b % 10;
            b /= 10;
        }
        return suma == sumb;
    }



    static String[] test4 = {"64333639502","65953866768","17845691654","87148775908","58954177897","70439926174","48059986638","47548857440","18418180516","06364956881","01866627626","36824890579","14672385151","71207752868"};
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        // int n = nums.length;
        int m = queries.length;
        int[] res = new int[m];
        // for (int j = 0; j < m; j++) {
        //     int[] q = queries[j];
        //     int trim = q[1];
        //     int k = q[0];
        //     Queue<Node> que = new PriorityQueue<>((a, b) -> a.x.compareTo(b.x) == 0 ? a.y - b.y : a.x.compareTo(b.x));
        //     long[] arr = new long[n];
        //     for (int i = 0 ; i < n; i++) {
        //         String tmp = nums[i].substring(nums[i].length() - trim, nums[i].length());
        //         // System.out.println(tmp + "    trim = " + trim) ;
        //         long a = Long.parseLong(tmp);
        //         arr[i] = a;
        //         que.offer(new Node(tmp, i));
        //     }
        //     while (k-- > 1) {
        //         // System.out.println(Arrays.toString(c));
        //     }
        //     System.out.println(" jifdjiajfidajf");
        // }
        return res;
    }

    
    
}

class Node {
    String x;
    int y ;
    public Node(String x, int y) {
        this.x = x;
        this.y = y;
    }
}
