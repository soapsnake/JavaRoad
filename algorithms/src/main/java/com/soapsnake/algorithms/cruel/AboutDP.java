package com.soapsnake.algorithms.cruel;

import java.util.Arrays;

/**
 *xxxx
 * Created on 2022-02-06
 */
public class AboutDP {

    //1473
    static final int INFTY = Integer.MAX_VALUE / 2;
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        for (int i = 0; i < m; ++i) {
            --houses[i];
        }
        int[][][] dp = new int[m][n][target];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], INFTY);
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (houses[i] != -1 && houses[i] != j) {
                    continue;
                }

                for (int k = 0; k < target; ++k) {
                    for (int j0 = 0; j0 < n; ++j0) {
                        if (j == j0) {
                            if (i == 0) {
                                if (k == 0) {
                                    dp[i][j][k] = 0;
                                }
                            } else {
                                dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k]);
                            }
                        } else if (i > 0 && k > 0) {
                            dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j0][k - 1]);
                        }
                    }

                    if (dp[i][j][k] != INFTY && houses[i] == -1) {
                        dp[i][j][k] += cost[i][j];
                    }
                }
            }
        }

        int ans = INFTY;
        for (int j = 0; j < n; ++j) {
            ans = Math.min(ans, dp[m - 1][j][target - 1]);
        }
        return ans == INFTY ? -1 : ans;
    }

    //2140
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            long points = questions[i][0];
            dp[i] = points;
            int indexRange = questions[i][1];
            if (i + indexRange < n) {
                dp[i] += dp[i + indexRange + 1];
            }
            dp[i] = Math.max(dp[i], dp[i + 1]);
        }
        return dp[0];
    }

    public void testR() {
        int[] a = {};
        int[] b = {};
        for (int i = 0; i < a.length; i++) {
            insert(b, i, i, a[i]);
        }
    }

    private void insert(int[] b, int l, int r, int c) {
        b[l] += c;
        b[r] -= c;
    }

    //KMP算法
    public boolean KMP(String s, String p) {
        int[] nextIntkey = this.buildNext(p);
        boolean res = false;
        int i = 0, j = 0;

        //kmp的最典型特征是: 文本串指针i绝不后退
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                //当文本串与模式串匹配时
                i++;
                j++;
            } else {
                //失配时,模式串向右移动的位数 = 已匹配字符数 - 失配字符上一位字符对应的next[]中该字符对应的最大长度值
                if (nextIntkey[j] == -1) {
                    i++;
                    j = 0;
                } else {
                    j = nextIntkey[j];
                }
            }
            if (j == p.length()) {
                //当模式串与文本串完全匹配时
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * 1. kmp求解next数组的过程其实是一个动态规划
     * 2. 求解最长前后缀公共子串长度的过程,是一种思想:退而求其次!!不是最优解那就尝试次优解,而不是每次都从零开始!
     * 3. kmp利用next进行模式匹配的过程,也是退而求其次!!不追求一失败就从零开始
     *
     */
    private int[] buildNext(String pattern) {
        int[] dp = new int[pattern.length()];
        dp[0] = -1;
        for (int i = 1, k = 0; i < dp.length; i++) {
            while (k >= 0 && pattern.charAt(k) != pattern.charAt(i)) {
                k = dp[k - 1];   //求解最长公共子串长度的过程,是一种思想:退而求其次,而不是每次都从头开始
                //这个求k的过程其实是一种逆向的思维,如果5个最长公共达不到,那我不妨试试4个,3个,2个?不要一失败就从1个字符从头来过
            }
            if (pattern.charAt(k) == pattern.charAt(i)) {
                k++;
            }
            dp[i] = k;  //注意哈,到这里k加过1了
        }
        return dp;
    }

    //2002
    public int maxProduct(String s) {
        int n = s.length(), m = 1 << n;
        char[] str = s.toCharArray();
        int[] count = new int[m];

        // 记录所有合法状态的字符串长度
        for (int i = 1; i < m; i++) {
            if (check(str, i)) {
                count[i] = Integer.bitCount(i);
            }
        }

        int res = 0;
        // 对 s 的每个子序列进行子集枚举
        for (int i = 1; i < m; i++) {
            int split = i >> 1;
            // 由于 j 与 i ^ j 是互补的关系，即 j ^ i ^ j = i
            // 因此只需枚举到 i / 2 就可以了
            for (int j = (i - 1) & i; j > split; j = (j - 1) & i) {
                // 假设 i 代表字符串 "etcdec"，j 为 i 的子集
                // 且 j = "ete" and i ^ j = "cdc" 时，记录答案
                res = Math.max(res, count[j] * count[i ^ j]);
            }
        }

        return res;
    }

    private boolean check(char[] s, int state) {
        int left = 0, right = s.length - 1;

        // 检查 state 对应的子序列是不是回文串
        while (left < right) {
            // 将 left 和 right 对应上 「状态所对应的字符」 位置
            while (left < right && (state >> left & 1) == 0) {
                left++;
            }
            while (left < right && (state >> right & 1) == 0) {
                right--;
            }
            if (s[left] != s[right]) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(Integer.bitCount(16));
        System.out.println(Integer.toBinaryString(16));


        System.out.println(Integer.bitCount(1));
        System.out.println(Integer.toBinaryString(1));


        System.out.println(Integer.bitCount(2));
        System.out.println(Integer.toBinaryString(2));



        System.out.println(Integer.bitCount(3));
        System.out.println(Integer.toBinaryString(3));



    }


}
