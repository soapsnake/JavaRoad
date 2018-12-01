package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/1 21:51
 */
public class Question453 {

    private int recur = 0;
    public int minMoves2(int[] nums) {
        int maxIndex = 0;
        int con = 1;
        int biggest = Integer.MIN_VALUE;
        int minnest = Integer.MAX_VALUE;
        int diff = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            //找出并记录数组中最大的数字和其索引
            if (nums[i] != nums[i + 1]) {
                int temp = nums[i] > nums[i + 1] ? nums[i] : nums[i + 1];
                int min = nums[i] > nums[i + 1] ? nums[i + 1] : nums[i];
                int tempIndex = nums[i] > nums[i + 1] ? i : i + 1;
                if (temp > biggest) {
                    biggest = temp;
                    maxIndex = tempIndex;
                }
                if (min < minnest) {
                    minnest = min;
                }
                diff = biggest - minnest;
            } else {
                con++;
            }
        }
        if (con != nums.length) {
            //发现了不等现象
            for (int i = 0; i < nums.length; i++) {
                if (i != maxIndex) {
                    nums[i] += diff;
                }
            }
            System.out.println(Arrays.toString(nums));
            recur += diff;
            minMoves(nums);
        } else {
            return recur;
        }
        return recur;
    }

    //把n - 1个元素进行加1操作,反过来,其实就是把某一个元素进行减1操作
    public int minMoves(int[] nums) {
        //todo

        return 0;
    }

        public static void main(String[] args) {
        Question453 question453 = new Question453();
        int[] tar = {1, 1, 712937829};
        System.out.println(question453.minMoves(tar));
    }
}
