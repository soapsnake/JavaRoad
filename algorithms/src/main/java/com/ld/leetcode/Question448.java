package com.ld.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question448 {

    //题目要求不能使用其他内存(除了数组和list)来解题,但是我这里使用了set,算是违规了
    public List<Integer> findDisappearedNumbers(int[] nums) {
            if (nums == null || nums.length == 0){
                return new ArrayList<>();
            }

        Set<Integer> set = new HashSet<>();
            for (int i=1;i<nums.length + 1;i++){
                set.add(i);
            }

            for (int j=0;j<nums.length;j++){
                set.remove(nums[j]);
                }
        List<Integer> res = new ArrayList<>(set);
            return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1};

        Question448 question448 = new Question448();
        ArrayUtils.printList(question448.findDisappearedNumbers(nums));
    }


}
