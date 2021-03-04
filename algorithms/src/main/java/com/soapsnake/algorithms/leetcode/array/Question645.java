package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-09 00:00
 */
public class Question645 {

//    public static void main(String[] args) {
//        Question645 question645 = new Question645();
//        int[] nums = {1, 2, 2, 4};
//        System.out.println(Arrays.toString(question645.findErrorNums(nums)));
//    }

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

    //leetcode268
    public static int missingNumber(int[] nums) {
        //思路:nums.length+1,算出总和,然后把nums总和加起来,算差值
        int n = nums.length;
        int should = n * (n + 1) / 2;
        int real = 0;
        for (int i : nums) {
            real += i;
        }
        return should - real;
    }

    public static int missingNumber2(int[] nums) {
        //异或运算: a ^ a = 0, a ^ !a = 1(注意a要转成二进制,每一位单独异或,结果再转成十进制)
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {9,6,4,2,3,5,7,0,1};
//        int[] nums = {0};
//        System.out.println(missingNumber(nums));

//        System.out.println(1 ^ 1);

        int a = 1;
        int b = 10;
        int c = 3 ^ 5;
        System.out.println(c);

//        System.out.println(2 ^ 1);
    }

}
