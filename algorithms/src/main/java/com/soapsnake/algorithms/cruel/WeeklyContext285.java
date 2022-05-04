package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WeeklyContext285 {

    private static boolean isPalind(long n) {
        if (n < 10) return true;
        long m = n;
        long k = 0;
        while (m > 0) {
            long b = m % 10;
            k = k * 10 + b;
            m /= 10;
        }
        return k == n;
    }

    public static void main(String[] args) {
        WeeklyContext285 weeklyContext285 = new WeeklyContext285();
        int[] q = {1, 2, 3, 4, 5, 90};
        int le = 3;

        System.out.println(1 % 100000);
        System.out.println(100 % 100000);
        System.out.println(1000 % 100000);
        System.out.println(10000 % 100000);
        System.out.println(100000 % 100000);
        System.out.println(1000000 % 100000);
        System.out.println(10000000 % 100000);

        //System.out.println(Arrays.toString(weeklyContext285.kthPalindrome4(q, le)));
    }

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            list1.add(nums1[i]);
        }
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            list2.add(nums2[i]);
        }
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);
        Set<Integer> set1clone = new HashSet<>(set1);
        set1.removeAll(set2);
        set2.removeAll(set1clone);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>(set1));
        res.add(new ArrayList<>(set2));
        return res;
    }

    public long[] kthPalindrome(int[] queries, int intLength) {
        long start = 1;
        long end = 9;
        while (--intLength > 0) {
            start *= 10;
            end = end * 10 + 9;
        }
        long max = Long.MIN_VALUE;
        for (int i = 0; i < queries.length; i++) {
            max = Math.max(max, queries[i]);
        }
        int n = queries.length;
        System.out.println("start" + start);
        System.out.println("end" + end);
        List<Long> res = new ArrayList<>();
        while (res.size() < max && start <= end) {
            if (isPalind(start)) res.add(start);
            start++;
        }
        System.out.println(res);
        long[] ans = new long[n];
        for (int i = 0; i < ans.length; i++) {
            long temp = -1;
            if (queries[i] - 1 < res.size()) {
                temp = res.get(queries[i] - 1) == null ? -1 : res.get(queries[i] - 1);
            }
            ans[i] = temp;
        }
        return ans;
    }

    public long[] kthPalindrome2(int[] queries, int intLength) {
        long start = 1;
        long end = 9;
        while (--intLength > 0) {
            start *= 10;
            end = end * 10 + 9;
        }
        long max = Long.MIN_VALUE;
        for (int i = 0; i < queries.length; i++) {
            max = Math.max(max, queries[i]);
        }
        int n = queries.length;
        List<Long> res = new ArrayList<>();
        while (res.size() < max && start <= end) {
            if (isPalind(start)) res.add(start);
            start++;
        }
        long[] ans = new long[n];
        for (int i = 0; i < ans.length; i++) {
            long temp = -1;
            if (queries[i] - 1 < res.size()) {
                temp = res.get(queries[i] - 1) == null ? -1 : res.get(queries[i] - 1);
            }
            ans[i] = temp;
        }
        return ans;
    }

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i < n; i++) {
            List<Integer> pile = piles.get(i);
            int max = Math.min(k, pile.size());
            int[] preSum = new int[pile.size() + 1];
            preSum[0] = 0;
            for (int j = 0; j < max; j++) {
                preSum[j + 1] = preSum[j] + pile.get(j);
            }
            for (int j = 1; j <= k; j++) {
                for (int l = 0; l <= Math.min(pile.size(), j); l++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - l] + preSum[l]);
                }
            }
        }
        return dp[n][k];
    }

    public int maxValueOfCoins1(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int m = piles.get(0).size();
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            List<Integer> pile = piles.get(i - 1);
            int max = Math.min(k, pile.size());   //栈有可能有5层,但是k如果只有3的话,最多就只能取3个
            int[] sum = new int[max + 1];
            sum[0] = 0;
            for (int l = 0; l < max; l++) {
                sum[l + 1] = sum[l] + pile.get(l);   //前缀和
            }
            for (int j = 1; j <= k; j++) {
                for (int l = 0; l <= Math.min(j, pile.size()); l++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - l] + sum[l]);
                }
            }
        }
        return dp[piles.size()][k];
    }

    public int minDeletion(int[] nums) {
        if (nums.length == 0)
            return 0;
        Integer prev = null;
        int ci = 0;
        for (int i = 0; i < nums.length; i++) {
            if (prev != null) {
                if (nums[i] != prev) {
                    ci += 2;
                    prev = null;
                }
            } else {
                prev = nums[i];
            }
        }
        return nums.length - ci;
    }

    public long[] kthPalindrome3(int[] queries, int intLength) {

        //先判断回文数字长度是偶数还是奇数
        boolean isOdd = intLength % 2 != 0;
        int half = intLength / 2;  //中位数索引
        if (isOdd) {
            half++;   //奇数的话指向最中间的数字
        }
        long min = (long) Math.pow(10, half - 1);  //如果是3的话,   就是100
        long max = (long) (Math.pow(10, half) - 1);  //如果是3的话, 就是999
        long[] res = new long[queries.length];       //结果数组
        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            if (min + query - 1 > max) {
                res[i] = -1;
                continue;
            }

            //求比100大的第i个数字 = 100 + i - 1
            //求比100大的第i个回文数字 = 100 + i - 1
            res[i] = make(min + query - 1, isOdd);
        }
        return res;
    }

    private long make(long l, boolean isOdd) {
        String str = String.valueOf(l);
        StringBuilder sb = new StringBuilder(str);
        long val = 0;
        if (isOdd) {
            val = Long.parseLong(str + sb.reverse().substring(1));
        } else {
            val = Long.parseLong(str + sb.reverse());
        }
        return val;
    }

    public long[] kthPalindrome4(int[] queries, int intLength) {
        boolean odd = intLength % 2 != 0;
        int half = odd ? intLength / 2 + 1 : intLength / 2;
        long[] res = new long[queries.length];
        //一半数字的最大和最小值
        long low = (long) Math.pow(10, half - 1);
        long high = (long) (Math.pow(10, half) - 1);
        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            if (low + query - 1 > high) {
                res[i] = -1;
                continue;
            }
            res[i] = build(low + query - 1, odd);
        }
        return res;
    }

    private long build(long low, boolean odd) {
        String left = String.valueOf(low);
        String right = "";
        if (odd) {
            right = new StringBuilder(left).reverse().substring(1);
            right = new StringBuilder(left).reverse().substring(1);

        } else {
            right = new StringBuilder(left).reverse().toString();
        }
        System.out.println("l = " + left);
        System.out.println("r = " + right);

        return Long.parseLong(left + right);
    }

}
