package com.soapsnake.algorithms.leetcode.number;

import java.util.List;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-04-08
 */
public class Question526 {

    //leetcode526
    public int countArrangement(int N) {
        if (N == 0) {
            return 0;
        }
        backTrace(N, 1, new int[N]);
        return count;
    }
    int count = 0;
    private void backTrace(int N, int pos, int[] used) {
        if (pos > N) {
            count++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (used[i] == 0 && (i % pos == 0 || pos % i == 0)) {
                //通过赋值used[i]为0或者1来完成回退
                used[i] = 1;
                backTrace(N, pos + 1, used);
                used[i] = 0;
            }
        }
    }
}
