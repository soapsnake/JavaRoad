package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-09 20:27
 */
public class Question189 {

    public static void main(String[] args) {
        Question189 question189 = new Question189();
        int[] nums = {-1};
        int k = 2;
        question189.rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    //todo 这道题没那么简单
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        System.out.println("k = " + k);
        this.reverseArr(nums, 0, nums.length - 1);
        this.reverseArr(nums, 0, k - 1);
        this.reverseArr(nums, k, nums.length - 1);
    }

    private void reverseArr(int[] nums, int start, int end) {
        while (end > start) {
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
    }
}
