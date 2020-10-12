package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-09 00:00
 */
public class Question645 {

    public static void main(String[] args) {
        Question645 question645 = new Question645();
        int[] nums = {1, 2, 2, 4};
        System.out.println(Arrays.toString(question645.findErrorNums(nums)));
    }

    /**
     * Input: nums = [1,2,2,4]
     * Output: [2,3]
     * 首先找到重复的数字,然后给出重复数正确应该是多少
     */
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            // the index we should to put abs(nums[i])
            int index = Math.abs(nums[i]) - 1;
            // if that position is already taken, i.e. negative
            // then it is duplicate
            if (nums[index] < 0) {
                res[0] = index + 1; // duplicate
            } else {
                // put it by marking negative
                nums[index] = -nums[index];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            // index i is not put, then i + 1 missing
            if (nums[i] > 0) {
                res[1] = i + 1;
            }
        }
        return res;
    }
}
