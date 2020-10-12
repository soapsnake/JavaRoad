package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-02 20:44
 */
public class Question628 {

    public static void main(String[] args) {
        Question628 question628 = new Question628();
        int[] nums = {1, 2, 3, 4};
        System.out.println(question628.maximumProduct(nums));
    }

    //一把过,这次真心牛逼,其实这道题总共就分两种情况:
    public int maximumProduct(int[] nums) {
        int res = 1;
        if (nums.length <= 3) {
            for (int i = 0; i < nums.length; i++) {
                res *= nums[i];
            }
            return res;
        }
        Arrays.sort(nums);


        //第一种情况
        //如果最小数字大于0,那么取最后三个
        //如果最大数小于0,那么取最后三个
        if (nums[0] > 0 || nums[nums.length - 1] < 0) {
            for (int i = nums.length - 1; i > nums.length - 4; i--) {
                res *= nums[i];
            }
            return res;
        }
        //如果0在中间,这个不一定了,如果负数只有一个,那么最右边3个,如果负数超过两个,那么比较最右三个与最左两个乘以最右一个
        for (int i = 0; i < nums.length; i++) {
            if (nums[i + 1] > 0) {
                //第一种情况
                for (int j = nums.length - 1; j > nums.length - 4; j--) {
                    res *= nums[j];
                }
                return res;
            } else {
                //第二种情况
                int product1 = nums[0] * nums[1] * nums[nums.length - 1];
                int product2 = 1;
                for (int j = nums.length - 1; j > nums.length - 4; j--) {
                    product2 *= nums[j];
                }
                return Math.max(product1, product2);
            }
        }
        return 0;
    }
}
