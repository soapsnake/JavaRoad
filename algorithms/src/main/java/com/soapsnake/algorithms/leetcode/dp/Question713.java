package com.soapsnake.algorithms.leetcode.dp;

/**
 * 
 * Created on 2020-09-28
 */
public class Question713 {

    /**
     *
     * Input: nums = [10, 5, 2, 6], k = 100
     * Output: 8
     * Explanation: The 8 subarrays that have product less than 100
     * are: [10], [5], [2], [6], [10, 5], [5, 2], [2,6], [5, 2, 6].
     * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
     *
     * The idea is always keep an max-product-window less than K;
     * Every time shift window by adding a new number on the right(j), if the product is greater than k, then try to
     * reduce numbers on the left(i), until the subarray product fit less than k again, (subarray could be empty);
     * Each step introduces x new subarrays, where x is the size of the current window (j + 1 - i);
     * example:
     * for window (5, 2), when 6 is introduced, it add 3 new subarray: (5, (2, (6)))
     *         (6)
     *      (2, 6)
     *   (5, 2, 6)
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        int cnt = 0;
        int pro = 1;
        for (int i = 0, j = 0; j < nums.length; j++) {
            //窗口右侧往右移动
            pro *= nums[j];
            //当引入新的数字导致总乘积大于k后
            while (i <= j && pro >= k) {
                //窗口左侧往右走,需要把之前的乘积刨除掉
                pro /= nums[i++];
            }
            //到这里,相当于当前窗口又找到了一个满足条件的子数组,这个j-i+1有点难以想到
            cnt += j - i + 1;
        }
        return cnt;
    }



}
