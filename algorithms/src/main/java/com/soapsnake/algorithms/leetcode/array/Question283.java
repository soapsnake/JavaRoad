package com.soapsnake.algorithms.leetcode.array;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 */
class Question283 {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        Question283 question283 = new Question283();
        question283.moveZeroes(nums);

        ArrayUtils.printArr(nums);

    }

    //慢速解法,更快的解法:为0就把后面的数字插到当前位置来,最后不足的数组位置补0
    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

}
