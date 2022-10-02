package com.soapsnake.algorithms.weekly;

import com.sun.org.apache.regexp.internal.REUtil;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-09-04
 * JavaRoad
 */
public class WeeklyContext309 {


    public boolean checkDistances(String s, int[] distance) {
        //读题: distance i 代表 第 -'a'个字符, 1. 如果该字符在s中没有出现,那么直接跳过,
        //如果有出现,那么计算相同字符中的出现次数.
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == null) {
                map.put(c, i);
            } else {
                map.put(c, i - map.get(c) - 1);
            }
        }
        for (int i = 0; i < distance.length; i++) {
            char c = (char) (i + 'a');
            if (map.containsKey(c)) {
                if (map.get(c) != distance[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        //到i时最长子数组的长度是dp[i];
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = -1;
        for (int i=  1; i < n; i++) {
            if (valid(nums, i - 1, dp[i - 1], nums[i])) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private boolean valid(int[] arr, int end , int len, int num) {
        for (int i = end - len + 1; i <= end; i++) {
            if ((arr[i] & num) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        WeeklyContext309 weeklyContext309 = new WeeklyContext309();
        int start= 2;
        int end = 5;
        int k = 10;
        //System.out.println(weeklyContext309.numberOfWays(start, end, k));
        int[] nums = {178830999,19325904,844110858,806734874,280746028,64,256,33554432,882197187,104359873,453049214,820924081,624788281,710612132,839991691};
        //System.out.println(weeklyContext309.longestNiceSubarray(nums));  //4
        StringBuilder sb = new StringBuilder("123456");
        System.out.println(sb.replace(2, 2, "#"));

    }
    int mod = (int) 1e9+7;
    public int numberOfWays(int startPos, int endPos, int k) {
        //读题: 每次可以向左,或者向右移动一位,求移动k次从startPos到endPos的总方法数
        // 每次可以+1, 也可以-1,表示每一次都有两种走法,青蛙跳台阶?
        Map<String, Long> memo = new HashMap<>();
        return (int) dfs(startPos, endPos, k, memo);
    }
    private long dfs(int startPos, int endPos, int k, Map<String, Long> memo) {
        if (startPos == endPos && k != 0) {
            return 0;
        }
        if (k == 0 && startPos != endPos) {
            return 0;
        }
        if (startPos == endPos && k == 0) return 1L;
        String cur = startPos + "->" + k;
        if (memo.containsKey(cur)) return memo.get(cur);
        long res = dfs(startPos + 1, endPos, k, memo) +  dfs(startPos - 1, endPos, k, memo);
        memo.put(cur, res);
        return res;
    }
}

