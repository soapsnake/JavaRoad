package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeeklyContext284 {


    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                int j = i;
                while (j - i <=k && j < nums.length) {
                    set.add(j);
                    j++;
                }
                int m = i;
                while (i - m <= k && m >=0) {
                    set.add(m);
                    m--;
                }
            }
        }
        List<Integer> res = new ArrayList<>(set);
        res.sort(Integer::compareTo);
        return res;
    }

    public static int maximumTop(int[] nums, int k) {
        if (nums.length == 0) {
            return -1;
        }
        List<Integer> res = new ArrayList<>();
        int last = 0;
        for (int i = 0; i< nums.length && k >= 1; i++, k--) {
            res.add(nums[i]);
            if (i + 1 < nums.length) {
                last = nums[i + 1];
            } else {
                last = -1;
            }
        }
        if (res.size() <= 1 && last == -1) {
            return -1;
        }
        res.sort(Integer::compareTo);
        return Math.max(res.get(res.size() - 1), last);
    }


    public static void main(String[] args) {
        WeeklyContext284 weeklyContext284 = new WeeklyContext284();
        //int[] nums = new int[]{35,43,23,86,23,45,84,2,18,83,79,28,54,81,12,94,14,0,0,29,94,12,13,1,48,85,22,95,24,5,73,10,96,97,72,41,52,1,91,3,20,22,41,98,70,20,52,48,91,84,16,30,27,35,69,33,67,18,4,53,86,78,26,83,13,96,29,15,34,80,16,49};
        //int k = 15;


        int[] nums = new int[]{2};
        int k = 1;
        System.out.println(maximumTop(nums, k));
    }

    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        //i行4列,i个工具
        int[][] arr = new int[n][n];
        for (int i = 0; i < dig.length; i++) {
            int[] temp = dig[i];
            arr[temp[0]][temp[1]] = 1;
        }
        int res = 0;
        for (int[] row : artifacts) {
            int[] start = new int[2];
            start[0] = row[0];
            start[1] = row[1];

            int[] end = new int[2];
            end[0] = row[2];
            end[1] = row[3];
            if (reach(arr, start, end, start[0], start[1])) {
                res++;
            }
        }
        return res;
    }

    private static boolean reach(int[][] arr, int[] start, int[] end, int i, int j) {
        if (i >= end[0] || j >= end[1]) {
            return true;
        }
        if (arr[i][j] != 1) {
            return false;
        }
        return  reach(arr, start, end, i + 1, j) &&  reach(arr, start, end, i, j + 1);
}


    public int maximumTop2(int[] nums, int k) {


        Map<Integer, List<Integer>> map = new HashMap<>();
        int ans = -1;
        if (nums.length == 1 && k % 2 == 1) {
            return ans;
        }

        for (int i = 0; i < k - 1 && i < nums.length; i++) {
            ans = Math.max(ans, nums[i]);
        }
        if (k < nums.length) {
            ans = Math.max(ans, nums[k]);
        }
        return ans;

    }


}
