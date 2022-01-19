package com.soapsnake.algorithms.leetcode.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-27 19:07
 */
public class Question139 {

    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] rangeSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                rangeSum[i + 1][j + 1] = rangeSum[i + 1][j] + rangeSum[i][j + 1] - rangeSum[i][j] + mat[i][j];
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j) {
                int r1 = Math.max(0, i - k), c1 = Math.max(0, j - k), r2 = Math.min(m, i + k + 1), c2 = Math.min(n, j + k + 1);
                ans[i][j] = rangeSum[r2][c2] - rangeSum[r2][c1] - rangeSum[r1][c2] + rangeSum[r1][c1];
            }
        return ans;
    }

    boolean valid = false;

    public static void main(String[] args) {
        Question139 question139 = new Question139();
        String s = "ccbb";
        List<String> list = Arrays.asList("bc", "cb");
        System.out.println(question139.wordBreak(s, list));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        //dp含义: dp[i] = s的子串从s[0]到s[i]是否符合条件
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                //这里找的不是dp[i]和dp[i-1]的关系,而是对每一个i都从头扫描一遍进行计算
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                   //s切成两部分,0->j, j ->i, dp[j]为true代表0->j的子串满足
                    //j->i如果也满足,那么代表0->i的子串肯定是能满足条件了,所以dp[i]必定为true
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    //复杂度过高
    public boolean wordBreak2(String s, List<String> wordDict) {

        if (s == null) {
            return true;
        }

        backtrace(s, wordDict);
        return valid;
    }

    private void backtrace(String s, List<String> wordDict) {
        System.out.println(s);
        if (s.length() == 0) {
            valid = true;
        } else {
            for (String str : wordDict) {
                if (str.charAt(0) == s.charAt(0)) {
                    if (s.length() >= str.length() && s.substring(0, str.length()).equals(str)) {
                        backtrace(s.substring(str.length()), wordDict);
                    }
                }
            }
        }
    }

    public boolean wordBreak3(String s, List<String> wordDict) {
        if (null == s) {
            return true;
        }

        boolean[] match = new boolean[s.length()];
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (match[j] && wordDict.contains(s.substring(j, i))) {
                    match[i] = true;
                    break;
                }
            }
        }
        return match[s.length() - 1];
    }


    public int minFallingPathSum(int[][] matrix) {
        for (int i = 1; i < matrix.length; ++i)
            for (int j = 0; j < matrix.length; ++j)
                matrix[i][j] += Math.min(matrix[i - 1][j], Math.min(matrix[i - 1][Math.max(0, j - 1)], matrix[i - 1][Math.min(matrix.length - 1, j + 1)]));
        return Arrays.stream(matrix[matrix.length - 1]).min().getAsInt();
    }





}