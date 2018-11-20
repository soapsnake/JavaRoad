package com.ld.leetcode.array;

import java.util.Arrays;

class Question35 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 7, 8, 10};
        Question35 question35 = new Question35();
        System.out.println(question35.searchInsert2(nums, 8));
    }

    //二分法思想
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return target > nums[0] ? 1 : 0;
        }
        if (nums[nums.length / 2] == target) {
            return nums.length / 2;
        } else if (nums[nums.length / 2] > target) {
            searchInsert(Arrays.copyOfRange(nums, 0, nums.length / 2), target);
        } else {
            searchInsert(Arrays.copyOfRange(nums, nums.length / 2, nums.length - 1), target);
        }
        return 0;
    }

    public int searchInsert2(int[] nums, int target) {
        if (null == nums) {
            return 0;
        }
        if (nums.length == 1) {
            return target > nums[0] ? 1 : 0;
        }
        if (target < nums[0]) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            } else if (target > nums[i] && i + 1 < nums.length && target < nums[i + 1]) {
                return i + 1;
            }
        }
        return nums.length;
    }
}
