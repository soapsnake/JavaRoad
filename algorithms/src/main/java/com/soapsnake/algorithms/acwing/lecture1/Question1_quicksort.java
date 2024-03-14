package com.soapsnake.algorithms.acwing.lecture1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created on 2023-06-14
 */
public class Question1_quicksort {
    private static Scanner sc =new Scanner(System.in);
//    public static void main(String[] args) throws Exception {
//        int n = 10;
//        Queue<int[]> que = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
//        que.offer(new int[] {1, -1});
//        que.offer(new int[] {1, 2});
//        que.offer(new int[] {1, -1000});
//
//        System.out.println(Arrays.toString(que.poll()));
//
//
//
//        List<List<Integer>> listOfLists = new ArrayList<>();
//        dfs(1, listOfLists, n, new ArrayList<>());
//        for (List<Integer> innerList : listOfLists) {
//            for (Integer element : innerList) {
//                // 处理每个Integer元素
//                System.out.print(element + " ");
//            }
//            System.out.println(); // 可选：用于换行
//        }
//    }

    private static void dfs(int i, List<List<Integer>> res, int n, List<Integer> tmp) {
        if (tmp.size() == n) {
            res.add(new ArrayList<>(tmp)); // 修改此行，将tmp添加到结果列表中
        }
        for (int j = i; j <= n; j++) {
            if (tmp.contains(j)) continue;
            tmp.add(j); // 修改此行，不需要+1，因为j已经是从1开始的
            dfs(j, res, n, tmp); // 修改此行，递归时传入j + 1
            tmp.remove(tmp.size() - 1);
        }
    }


    /**
     *给定你一个长度为 n 的整数数列。
     *
     * 请你使用快速排序对这个数列按照从小到大进行排序并将排好序的数列按顺序输出。
     *
     * 输入格式
     * 输入共两行，第一行包含整数 n
     *
     * 第二行包含 n
     *  个整数（所有整数均在 1∼109
     *  范围内），表示整个数列。
     *
     * 输出格式
     * 输出共一行，包含 n
     *  个整数，表示排好序的数列。
     *
     * 数据范围
     * 1≤n≤100000
     * 输入样例：
     * 5
     * 3 1 2 4 5
     * 输出样例：
     * 1 2 3 4 5
     */




    public long maximumHappinessSum(int[] happiness, int k) {
        //
        int n = happiness.length;
        Arrays.sort(happiness);
        int total = happiness[n - 1];
        k--;
        int count = 1;
        for(int i = n - 2; i >= 0; i--) {
            if(k == 0) {
                break;
            }
            k--;
            if(happiness[i] - count >  0) {
                System.out.println("i = " + i + " dest = " + (happiness[i] - count));
                total += happiness[i] - count;
            }
            count++;
        }
        return total;
    }


    public static void main(String[] args) {
        Question1_quicksort q = new Question1_quicksort();
        int[] res = {7, 50, 3};
        int k = 3;
        String[] fdsfds = {"gfnt","xn","mdz","yfmr","fi","wwncn","hkdy"};
        //yuqi: ["g","x","z","r","i","c","h"]
        System.out.println(Arrays.toString(q.shortestSubstrings(fdsfds)));
    }

    public String[] shortestSubstrings(String[] arr) {
        //求每一个元素的最小字典序子字符串, 要求是该子字符串不能是其他元素的子字符串.
        //最暴力: 求每个元素的所有子字符串,挨个判断是否其他元素的子串,不是就加到集合里面.
        List<List<String>> allSub = new ArrayList<>();
        for(String s: arr) {
            List<String> a = allSubStr(s);
            allSub.add(a);
        }
//        System.out.println("al = " + allSub);
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            List<String> allOfDes = allSub.get(i);
            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                String b = arr[j];
                allOfDes.removeIf(sub -> isSub(b, sub));
            }
        }
        String[] res = new String[n];
        int k = 0;
        System.out.println("allSub = " + allSub);
        for(List<String> list : allSub) {
            System.out.println("pre list = " + list);
            list.sort((o1, o2) -> {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                }
                return o1.compareTo(o2);
            });
            System.out.println("list = " + list);
            res[k++] = list.isEmpty() ? "" : list.get(0);
        }
        return res;
    }

    private boolean isSub(String b, String sub) {
        int i = 0;
        int j = 0;
        while(i < b.length() && j < sub.length()) {
            if(b.charAt(i) == sub.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        boolean res = j == sub.length();
        return res;
    }

    private List<String> allSubStr(String s) {
        List<String> res = new ArrayList<>();
        for(int i =0; i < s.length(); i++) {
            for(int j = i + 1; j <= s.length(); j++) {
                res.add(s.substring(i, j));
            }
        }
        return res;
    }


}


