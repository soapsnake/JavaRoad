package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

public class Question215 {

    public static void main(String[] args) {
        Question215 quesiont215 = new Question215();
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(quesiont215.findKthLargest(nums, k));
    }

    public int findKthLargest(int[] nums, int k) {

        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);  //升序

        return nums[nums.length - k];
    }
}
