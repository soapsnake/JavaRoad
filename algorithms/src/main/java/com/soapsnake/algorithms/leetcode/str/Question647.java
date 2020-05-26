package com.soapsnake.algorithms.leetcode.str;

public class Question647 {

    /**
     * 找寻一个字符串中所有的回文子串的个数
     * 主要思路就是对子字符串进行扩展,如果源字符串是回文串,那么在字符串两端各加一个字符,然后看新字符串是否还是回文串
     */
    int count = 0;
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }
        return count;
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }

    //用线连A和B中相等的数字,求最多可以画多少条不相交的线
//if A[i] and B[j] are same, increment count and advance i and j and work on the remaning arrays.
//    Otherwise, we advance i and check the count on the remaining arrays,
    //   in another case we advance j and check, choose the max from the two sub-problems.
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                if (A[i - 1] == B[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
        return dp[m][n];
    }
}
