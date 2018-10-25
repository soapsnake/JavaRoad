package com.ld.leetcode.array;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class Question136 {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 3, -2, -1, -2, 3, 8};
        Question136 question136 = new Question136();
        System.out.println("res" + question136.singleNumber(nums));
    }

    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }
}
