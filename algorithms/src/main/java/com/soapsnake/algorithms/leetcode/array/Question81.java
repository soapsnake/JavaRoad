package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-06 23:08
 */
public class Question81 {

    /**
     * 在一个本来是升序的但是被重排的数组里,找出target是否存在
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        int segment = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i + 1] < nums[i]) {
                segment = i;
            }
        }
        if (segment != -1) {
            return binerSearch(nums, 0, segment, target) || binerSearch(nums, segment + 1, nums.length - 1, target);
        } else {
            return binerSearch(nums, 0, nums.length - 1, target);
        }

    }

    private boolean binerSearch(int[] nums, int left, int right, int target) {
        if (left > right) {
            return false;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return true;
        } else if (nums[mid] > target) {
            return binerSearch(nums, left, mid - 1, target);
        } else {
            return binerSearch(nums, mid + 1, right, target);
        }
    }
}
