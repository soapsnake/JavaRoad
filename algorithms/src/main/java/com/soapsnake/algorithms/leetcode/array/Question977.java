package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-02 21:47
 */
public class Question977 {

    public int[] sortedSquares(int[] nums) {
        //思路,把所有的负数全变成正的(反正要平方的),然后重新排序,最后求平方(排序整数平方后仍然是有序的)
        //复杂度nlog(n),取决于排序
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
            } else {
                break;
            }
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i] * nums[i];
        }
        return res;
    }


    public int[] sortedSquares2(int[] nums) {
        int[] res = new int[nums.length];
        int l = 0, r = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (Math.abs(nums[l]) >= Math.abs(nums[r])) {
                res[i] = nums[l] * nums[l];
                l++;
            } else {
                res[i] = nums[r] * nums[r];
                r--;
            }
        }
        return res;
    }
}
