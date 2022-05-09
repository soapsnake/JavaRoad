package com.soapsnake.algorithms.leetcode.dp;

/**
 * 
 * Created on 2021-03-27
 */
public class Kmp {

    private int[][] dp;
    private String pattern;
    private int M;

    public Kmp(String pattern) {
        this.dp = generateNext(pattern);
        this.pattern = pattern;
        this.M = pattern.length();
    }

    private int[][] generateNext(String pattern) {
        int[][] dp = new int[pattern.length()][256];  //ascii码的数量一共是256个

        dp[0][pattern.charAt(0)] = 1;

        int X = 0;

        for (int i = 1; i < pattern.length(); i++) {
            for (int j = 0; j < 256; j++) {
                if (pattern.charAt(i) == j) {
                    dp[i][j] = i + 1;
                } else {
                    dp[i][j] = dp[X][j];
                }
            }
            X = dp[X][pattern.charAt(i)];
        }
        return dp;
        
    }

    public int search(String txt) {
        int N = txt.length();
        int i = 0;
        for (int j = 0; j < N; j++) {
            i = dp[i][txt.charAt(j)];
            if (i == M) {
                return j - M + 1;
            }
        }
        return -1;
    }

    public static int search(String S, String P) {
        int n = P.length();
        int m = S.length();
        char[] p = new char[n + 1];
        char[] s = new char[m + 1];
        for (int i = 1 ; i <= n; i++) p[i] = P.charAt(i - 1);
        for (int i = 1; i <= m; i++) s[i] = S.charAt(i - 1);
        int[] next = new int[n + 1];
        for (int i = 2, j = 0; i <= n; i++ ) {
            while (j != 0  && p[i] != p[j + 1]) j = next[j];
            if (p[i] == p[j + 1]) j++;
            next[i] = j;
        }
        for (int i = 1, j = 0; i <= m; i++) {
            while (j != 0 && s[i] != p[j + 1]) j = next[j];
            if (s[i]  == p[j + 1]) j++;
            if (j == n) return i - n;
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "abcjfdiasjifjdosajifjdisaojfdsa";
        String p = "jdosajifjbisa";
        System.out.println(search3(s, p));
    }

    public static int search2(String P, String S) {
        int n = P.length();
        int m = S.length();
        char[] s = new char[m + 1];
        char[] p = new char[n + 1];
        for (int i = 1; i <= n; i++) p[i] = P.charAt(i - 1);
        for (int i = 1; i <= m; i++) s[i] = S.charAt(i - 1);

        int[] next = new int[n + 1];
        for (int i = 2, j = 0; i <= n; i++) {
            while (j != 0 && p[i] != p[i + 1]) j = next[j];
            if (p[i] == p[j +1 ]) j++;
            next[i] = j;
        }
        for (int i = 1, j = 0; i <= m; i++) {
            while (j != 0 && s[i] != p[i + 1]) j = next[j];
            if (s[i] == p[j + 1]) j++;
            if (j == n) return i - n;
        }
        return -1;
    }

    public static int search3(String S, String P) {
        int n = P.length();
        int m = S.length();
        char[] p = new char[n  + 1];
        char[] s = new char[m + 1];
        for (int i = 1; i <=n; i++ ) p[i] = P.charAt(i - 1);
        for (int i = 1; i <= m; i++) s[i] = S.charAt(i - 1);
        int[] next = new int[n + 1];
        for (int i = 2, j = 0; i <= n; i++) {
            while (j != 0 && p[i] != p[j + 1]) j = next[j];
            if (p[i] == p[j + 1]) j++;
            next[i] = j;
        }
        for (int i = 1, j = 0; i <= m; i++) {
            while (j != 0 && s[i] != p[j + 1]) j = next[j];
            if (s[i] == p[j + 1]) j++;
            if (j == n) return i - n;
        }
        return -1;
    }

}
