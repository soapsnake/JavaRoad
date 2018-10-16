package com.ld.leetcode.array;

import java.util.Arrays;

/**
 * Given an array of 2n integers, your task is to group these integers into n pairs of integer,
 * say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 */
public class Question561 {


    //思路:先对数组进行排序,然后从小到大两两连续数字构成一对,这种形式获得的数组,就是满足要求的数对,关键是排序
    //算法的复杂度
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);   //还有一种写法:nums.stream().sort().toArray() 不过这样写会导致时间复杂度飙升,为什么了?
        int temp = 0;
        for (int i=0;i < nums.length;i+=2){
            temp += Math.min(nums[i], nums[i+1]);
        }

        return temp;
    }

    public static void main(String[] args) {
        Question561 question561 = new Question561();
        int[] nums = {1,4,3,2};
        System.out.println(question561.arrayPairSum(nums));
    }
}
