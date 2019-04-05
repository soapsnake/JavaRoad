package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-05 23:07
 */
public class Question80 {

    public static void main(String[] args) {
        Question80 question80 = new Question80();
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        question80.removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * Given nums = [1,1,1,2,2,3],
     * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
     * It doesn't matter what you leave beyond the returned length.
     */
    public int removeDuplicates(int[] nums) {
        int duplicates = 2;   //duplicates可以为任意变量,这里应题目的要求设置为2
        int allows = duplicates;
        if (nums.length == 0) return 0;
        int left = 1;     //left pointer points to the tail of the kept elements.
        for (int right = 0; right < nums.length - 1; right++)
            if (nums[right] != nums[right + 1]) {  //如果不重复
                nums[left++] = nums[right + 1];  //update the tail of the kept elements and set left++;
                allows = duplicates;             //reset the number of allowed duplicates back to 2
            } else if (--allows == 1) {
                nums[left++] = nums[right + 1]; //if we just met the second duplicates, update the tail of the kept elements and set left++;
            }
        return left;
    }
}
