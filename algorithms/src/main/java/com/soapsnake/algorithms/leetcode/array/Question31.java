package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-11 12:05
 */
public class Question31 {

    /**
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     */
    public void nextPermutation(int[] nums) {
        /**
         * 题目含义: 给定一个含n个数字的字符串,给出一个排列,新排列相比旧排列的区别:
         * 1.新排列顺序代表的数字应该比原排列要大;
         * 2.如果不存在该序列,那么把数组进行升序排列
         */
        if(nums == null || nums.length <= 1) {
            return;
        }
        int i = nums.length - 2;
        while(i >= 0 && nums[i] >= nums[i + 1]) {
            i--; // Find 1st id i that breaks descending order
        }
        if(i >= 0) {                           // If not entirely descending
            int j = nums.length - 1;              // Start from the end
            while(nums[j] <= nums[i]) {
                j--;           // Find rightmost first larger id j
            }
            swap(nums, i, j);                     // Switch i and j
        }
        reverse(nums, i + 1, nums.length - 1);       // Reverse the descending sequence
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void reverse(int[] A, int i, int j) {
        while(i < j) {
            swap(A, i++, j--);
        }
    }
}
