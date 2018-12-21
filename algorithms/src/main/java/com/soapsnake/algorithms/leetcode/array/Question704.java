package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-22 00:29
 */
public class Question704 {
    public int search(int[] nums, int target) {
        if (nums == null) {
            return 0;
        }
        int left = 0 ;
        int right = nums.length - 1;
        int tar1 =  this.recursive(left, right, nums, target);
        return tar1;
    }

    private int recursive(int left, int right, int[] nums, int target) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        System.out.println("left = " + left + " mid = " + mid + " right = " + right);
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] > target) {
            return recursive(left, mid - 1, nums, target);
        }
        if (nums[mid]< target) {
            return recursive(mid + 1, right, nums, target);
        }
        if (mid == 0 || mid == nums.length - 1) {
            return -1;
        }
        return mid;
    }

    public static void main(String[] args) {
        Question704 question704 = new Question704();
        int[] nums = {-1,0,3,5,9,12};
        int target = 2;
        System.out.println(question704.search(nums, target));
    }
}
