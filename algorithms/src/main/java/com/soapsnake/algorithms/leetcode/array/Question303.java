package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-13 01:48
 */
public class Question303 {
    private final int[] nums;

    public Question303(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        int res = 0;
            for (int m = i; m <= j; m++) {
                res += nums[m];
            }
            return res;
    }
}
