package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-13 12:03
 */
public class Question34 {

    /**
     * Example 1:
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     *
     * Example 2:
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     */
    //算法要求O(logn)的复杂度,必须二分了,这道题用了遍历版二分,所以多花了点时间
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if (nums.length == 0) {
            return res;
        }
        int tempIndex = -1;
        tempIndex = this.middleSearchIter(nums, target);
        if (tempIndex != -1) {
            res[0] = tempIndex;
            res[1] = tempIndex;
            int left = tempIndex;
            int right = tempIndex;
            while (left-- > 0 ) {
                if (nums[left] == target) {
                    res[0] = left;
                }
            }
            while (right++ < nums.length - 1) {
                if (nums[right] == target) {
                    res[1] = right;
                }
            }
        }
        return res;
    }

    /**
     * 遍历版二分
     * @param nums
     * @param target
     * @return
     */
    private int middleSearchIter(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int middle = -1;
        while (right >= left) {
            middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                return middle;
            }else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
     }

    public static void main(String[] args) {
        Question34 question34 = new Question34();

        int[] nums = {5,7,7,8,8,10};
        int tar = 8;
        System.out.println(Arrays.toString(question34.searchRange(nums, tar)));
    }
}
