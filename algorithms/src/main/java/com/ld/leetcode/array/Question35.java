package com.ld.leetcode.array;

import java.util.Arrays;

public class Question35 {

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return target > nums[0] ? 1: 0;
        }
        if (nums[nums.length / 2] == target){
            return nums.length / 2;
        }else if (nums[nums.length / 2] > target){
            searchInsert(Arrays.copyOfRange(nums, 0,nums.length /2 ), target);
        }else {
            searchInsert(Arrays.copyOfRange(nums, nums.length /2, nums.length - 1), target);
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,7,8,10};
        Question35 question35 = new Question35();
        System.out.println(question35.searchInsert(nums, 8));
    }
}
