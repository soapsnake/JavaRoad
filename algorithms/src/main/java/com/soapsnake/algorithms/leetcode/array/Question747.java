package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-01 18:22
 */
public class Question747 {

    public int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return -1;
        }
        int max = 0;
        int maxIndex = 0;
        int sec = 0;
        int secIndex = 0;
        if (nums[0] <= nums[1]) {
            max = nums[1];
            maxIndex = 1;
            sec = nums[0];
            secIndex = 0;
        } else {
            max = nums[0];
            maxIndex = 0;
            sec = nums[1];
            secIndex = 1;
        }

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > max) {
                sec = max;
                secIndex = maxIndex;
                max = nums[i];
                maxIndex = i;
            } else if (nums[i] > sec) {
                sec = nums[i];
                secIndex = i;
            }
        }
        if (max >= 2 * sec) {
            return maxIndex;
        } else {
            return -1;
        }
    }
}
