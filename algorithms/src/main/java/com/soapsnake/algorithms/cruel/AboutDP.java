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


}
