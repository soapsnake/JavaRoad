package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-26 21:57
 */
public class Question532 {

    //用双指针再做一次,
    //todo
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1] ) {
                continue;
            }
            for (int j = nums.length - 1; j > i; j--) {
                if (j != nums.length - 1 && nums[j] == nums[j + 1]) {
                    continue;
                }
                if (nums[j] - nums[i] == k) {
                    res++;
                    System.out.println("i = " + i + " j = " + j);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Question532 question532 = new Question532();
        int[] nus = {1, 1, 1, 1, 1};
        int k = 0;
        System.out.println(question532.findPairs(nus, k));
    }


}
