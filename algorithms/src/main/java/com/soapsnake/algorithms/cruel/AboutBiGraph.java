package com.soapsnake.algorithms.cruel;

import java.util.Arrays;
import java.util.List;

public class AboutBiGraph {

    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size();
        int n = cost.get(0).size();
        int msk = 1 << m;
        int[] dp = new int[msk];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int[] tmp = new int[msk];
            for (int l = 0; l < msk; l++) tmp[l] = Integer.MAX_VALUE;
            for (int k = 0; k < msk; k++) {
                for (int j = 0; j < m; j++) {
                    int mask = k | (1 << j);
                    if (dp[k] != Integer.MAX_VALUE) {
                        tmp[mask] = Math.min(tmp[mask], dp[k] + cost.get(j).get(i));
                    } 
                    if ((k & (1 << j)) == 0) {
                        if (tmp[k] != Integer.MAX_VALUE) {
                            tmp[mask] = Math.min(tmp[mask], tmp[k] + cost.get(j).get(i));
                        }
                    }
                }
            }
            dp = tmp;
        }
        return dp[msk - 1];
    }



}