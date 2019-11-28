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
     * 题目的含义是,数组中的数字最多只能重复一次,多的应该去除掉,然后计算清除重复后的数组的长度
     * Given nums = [1,1,1,2,2,3],
     * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
     * It doesn't matter what you leave beyond the returned length.
     */
    public int removeDuplicates(int[] nums) {
        int duplicates = 2;   //duplicates可以为任意变量,这里应题目的要求设置为2
        int allows = duplicates;
        if (nums.length == 0) {
            return 0;
        }
        int left = 1;     //left pointer points to the tail of the kept elements.
        for (int right = 0; right < nums.length - 1; right++) {
            if (nums[right] != nums[right + 1]) {  //如果不重复
                nums[left++] = nums[right + 1];  //update the tail of the kept elements and set left++;
                allows = duplicates;             //reset the number of allowed duplicates back to 2
            } else if (--allows == 1) {
                nums[left++] = nums[right + 1]; //if we just met the second duplicates, update the tail of the kept elements and set left++;
            }
        }
        return left;
    }


    public int removeDuplicates2(int[] nums) {
        final int duplicate = 2;  //可重复次数是可以配置的
        int allow = duplicate;
        int leftPointer = 1;  //这个指针一直指向合法数组的尾部
        for (int i = 0; i < nums.length - 1; i++) {
            //不重复的情况,左指针要跟着移动
            if (nums[i] != nums[i + 1]) {
                nums[leftPointer++] = nums[i + 1];  //往左划拉
                allow = duplicate;
                //出现了重复的时候,如果重复次数在允许范围内,那么只需要移动指针
            } else if (--allow >= 1) {
                nums[leftPointer++] = nums[i + 1];
            }
        }
        return leftPointer;
    }






















    }
