package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-12 09:59
 */
public class Question665 {

    //    int count;
//    static int count = 3;

    public Question665(int count) {
//        this.count = count;
    }

    public static void main(String[] args) {
        Question665 question665 = new Question665(2);
        int[] nums = {4, 2, 3};
        System.out.println(question665.checkPossibility(nums));
    }

    public boolean checkPossibility(int[] nums) {
//        count = 1;
//        System.out.println("local = " + count);
//        System.out.println("class = " + this.count);
//        System.out.println("static = " + Question665.count);
        if (nums == null) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        boolean test1 = true;
        boolean test2 = true;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] < nums[i - 1]) {
                //第一种尝试:
                int temp = nums[i - 1];
                nums[i - 1] = nums[i];
//                System.out.println("第一次nums[]:" + Arrays.toString(nums));
                for (int j = 0; j < nums.length; j++) {
                    if (j != 0 && nums[j] < nums[j - 1]) {
                        test1 = false;
                        break;
                    }
                }
                //第二种尝试:
                nums[i - 1] = temp;
                nums[i] = temp;
//                System.out.println("第二次nums[]:" + Arrays.toString(nums));
                for (int j = 0; j < nums.length; j++) {
                    if (j != 0 && nums[j] < nums[j - 1]) {
                        test2 = false;
                        break;
                    }
                }
                break;
            }
        }
        return test1 || test2;
    }

}
