package com.soapsnake.algorithms.leetcode.dp;

import com.sun.security.auth.NTUserPrincipal;

/**
 *
 * Created on 2020-03-31
 */
public class Question516 {

    //question516 最长回文字串
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int left = s.length() - 1; left >= 0; left--) {
            dp[left][left] = 1;
            for (int right = left + 1; right < s.length(); right++) {
                if (s.charAt(left) == s.charAt(right)) {
                    dp[left][right] = dp[left + 1][right - 1] + 2;
                } else {
                    dp[left][right] = Math.max(dp[left + 1][right], dp[left][right - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {

    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] dp = new int[nums.length + 1];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length];
    }

    public boolean isSubsequence(String s, String t) {
        //判断s是否是t的字串
        //思路两个指针分别指向t和s,相等时候都移动,不相等时只移动t
        if (s.length() > t.length()) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        int s_point = 0;
        int t_point = 0;
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(s_point) == t.charAt(t_point)) {
                s_point++;
                if (s_point == s.length() - 1) {
                    return true;
                }
                t_point++;
            } else {
                t_point++;
            }
        }
        return false;
    }

}
