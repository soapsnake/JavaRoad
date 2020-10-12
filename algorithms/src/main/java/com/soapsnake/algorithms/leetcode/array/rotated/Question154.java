package com.soapsnake.algorithms.leetcode.array.rotated;

/**
 *
 * Created on 2020-07-26
 */
public class Question154 {

    //leetcode154旋转数组最小值,如果nums中有重复数字的情况
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid = 0;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else if (nums[mid] < nums[hi]) {
                hi = mid;
            } else { // when num[mid] and num[hi] are same
                hi--;
            }
        }
        return nums[lo];
    }


    public int addDigits(int num) {
        String str = String.valueOf(num);
        if (str.length() < 2) {
            return num;
        }
        char[] ca = str.toCharArray();
        int res = 0;
        for (int i = 0; i < ca.length; i++) {
            res += ca[i] - '0';
        }
        return addDigits(res);
    }
}
