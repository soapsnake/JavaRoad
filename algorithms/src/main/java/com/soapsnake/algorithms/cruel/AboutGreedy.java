package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 
 * Created on 2022-01-24
 */
public class AboutGreedy {

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = Math.min(rowSum[i], colSum[j]);
                rowSum[i] -= res[i][j];
                colSum[j] -= res[i][j];
            }
        }
        return res;
    }

    //2136
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        list.sort((o1, o2) -> growTime[o2] - growTime[o1]);
        int ans = 0;
        int pt = 0, gt = 0;
        for (int index: list) {
            pt += plantTime[index];
            gt = pt + growTime[index];
            ans = Math.max(ans, gt);
        }
        return ans;
    }


}
