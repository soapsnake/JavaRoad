package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-11 19:14
 */
public class Question268 {

    public static void main(String[] args) {
        Question268 question268 = new Question268();
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(question268.missingNumber(nums));
    }

    public int missingNumber1(int[] nums) {
        //todo 使用xor运算符解决这个问题,只需要遍历一次
        return 0;
    }

    public int missingNumber(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1 && nums[0] == 1) {
            return 0;
        } else if (nums.length == 1 && nums[0] == 0) {
            return 1;
        }
        Arrays.sort(nums);
        if (nums[0] != 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length) {
                if (nums[i] + 1 != nums[i + 1]) {
                    return nums[i] + 1;
                }
            } else {
                return nums[i] + 1;
            }
        }
        return -1;
    }
}
