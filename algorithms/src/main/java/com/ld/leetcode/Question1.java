package com.ld.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class Question1 {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i=0;i<nums.length;i++){
            for (int j=nums.length - 1;j > 0;j--){
                if (nums[i] + nums[j] == target){
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    //解法2:字典保存法,这个思路可以用来处理递归计算中的重复计算问题
    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> temp = new HashMap<>();   //key = val, value = index
        for (int i=0;i<nums.length;i++){
            if (temp.get(target - nums[i]) != null){
                res[0] = i;
                res[1] = temp.get(target - nums[i]);
            }
            temp.put(nums[i], i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ques = new int[]{3,3};
        Question1 question1 = new Question1();
        ArrayUtils.printArr(question1.twoSum2(ques, 6));

//        for (int i = 1;i<20;i++) {
//            System.out.println(i % 10);
//        }
    }
}
