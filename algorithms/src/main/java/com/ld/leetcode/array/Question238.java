package com.ld.leetcode.array;

import java.util.Arrays;

public class Question238 {

    public static void main(String[] args) {
        Question238 question238 = new Question238();
        int[] tar = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(question238.productExceptSelf3(tar)));
    }

    /**
     * Given an array nums of n integers where n > 1,
     * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
     * 这里的product的含义是乘积,而不是产品
     * 本题难点在于时间复杂度只允许O(n)
     * Example:
     * Input:  [1,2,3,4]
     * Output: [24,12,8,6]
     */


    //平方级别时间复杂度解法
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int temp = 1;
            for (int j = 0; j < nums.length; j++) {
                if (j != i) {
                    temp *= nums[j];
                }
            }
            output[i] = temp;
        }
        return output;
    }

    //leetcode通过版,思路:如果全积为0,那么走平方级算法,如果全积不为0,那么只需要用全积除掉nums[i]即可
    public int[] productExceptSelf2(int[] nums) {
        int[] output = new int[nums.length];
        int temp = 1;
        for (int k = 0; k < nums.length; k++) {
            temp *= nums[k];
        }
        for (int i = 0; i < nums.length; i++) {
            if (temp != 0) {  //所有元素均不为0
                output[i] = temp / nums[i];
            } else {  //有元素为0
                int temp2 = 1;
                for (int j = 0; j < nums.length; j++) {
                    if (i != j) {
                        temp2 *= nums[j];
                    }
                }
                output[i] = temp2;
            }
        }
        return output;
    }

    //大神级程序员的解法,菜逼退散
    public int[] productExceptSelf3(int[] nums) {
        //TODO 需要debug搞懂
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }


}
