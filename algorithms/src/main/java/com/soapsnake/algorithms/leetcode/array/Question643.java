package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-09 18:53
 */
public class Question643 {

    public double findMaxAverage(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        if (k == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i + k > nums.length) {
                System.out.println("i= " + i + " k= " + k);
                break;
            }
            int temp = 0;
            for (int j = i; j < i + k; j++) {
                temp += nums[j];
            }
            System.out.println("temp= " + temp);
            max = Math.max(temp, max);
        }
        return (double) max / (double) k;
    }

    public static void main(String[] args) {
        Question643 question643 = new Question643();
        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        System.out.println(question643.findMaxAverage(nums, k));
    }
}
