package com.soapsnake.algorithms.leetcode.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question279 {

    /**
     * Example 1:
     * Input: n = 12
     * Output: 3
     * Explanation: 12 = 4 + 4 + 4.
     *
     * Example 2:
     * Input: n = 13
     * Output: 2
     * Explanation: 13 = 4 + 9.
     */

    /**
     * dp[n] indicates that the perfect squares count of the given n, and we have:
     * dp[0] = 0
     * dp[1] = dp[0]+1 = 1
     * dp[2] = dp[1]+1 = 2
     * dp[3] = dp[2]+1 = 3
     * dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 }    4 = 1 + 1 + 1 + 1 或者是 4 = 2 * 2
     *       = Min{ dp[3]+1, dp[0]+1 }     dp[3] + 1 = 3 + 1 =  4, dp[0] + 1 = 1 + 1 = 2
     *       = 1
     * dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 } 5 = 1 + 1 + 1 + 1 + 1 或者 5 = 2 * 2 + 1
     *       = Min{ dp[4]+1, dp[1]+1 }
     *       = 2
     * 						.
     * 						.
     * 						.
     * dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 }
     *        = Min{ dp[12]+1, dp[9]+1, dp[4]+1 }
     *        = 2
     * 						.
     * 						.
     * 						.
     * dp[n] = Min{ dp[n - i*i] + 1 },  i应该满足的条件: n - i*i >=0 && i >= 1, i++
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];  //dp数组的长度是n + 1
        Arrays.fill(dp, Integer.MAX_VALUE);  //数组每个数都是最大值
        dp[0] = 0;
        for(int i = 1; i <= n; ++i) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            while(i - j*j >= 0) {
                min = Math.min(min, dp[i - j*j] + 1);
                ++j;
            }
            dp[i] = min;
        }
        return dp[n];
    }


    //too compicated, overtime!!!!!!!1
    public int numSquares2(int n) {
        if (n == 1) return n;
        List<Integer> temp = new ArrayList<>();
        backtrace(n, temp, 1);
        return count;
    }
    int count = Integer.MAX_VALUE;
    private void backtrace(int n, List<Integer> tmp, int start) {
        int res = 0;
        for (int i : tmp) {
            res += i * i;
        }
        if (res == n) {
            System.out.println("tmp=" + tmp);
            count = Math.min(tmp.size(), count);
        } else if (res > n) {
            return;
        } else {
            for (int i = start; i < n; i++) {
                tmp.add(i);
                backtrace(n, tmp, i);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Question279 question279 = new Question279();
        System.out.println(question279.numSquares2(12));
    }
}
