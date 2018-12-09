package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/8 23:05
 */
public class Question26 {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int dup = 0;
        int base = nums[0] - 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == nums[i] && nums[j] != base) {
                    nums[j] = base;
                    dup++;
                }
            }
        }
        int[] newArr = new int[nums.length - dup];
        System.out.println("base.leng = " + nums.length + " newArr.length = " + newArr.length);
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != base) {
                newArr[k++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(newArr));
        return dup;
    }

    public static void main(String[] args) {
        Question26 question26 = new Question26();
        int[] arr = {1,1,2};
        System.out.println(question26.removeDuplicates(arr));
    }
}
