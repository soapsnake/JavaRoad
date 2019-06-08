package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Question228 {

    /**
     * Example 1:
     * Input:  [0,1,2,4,5,7]
     * Output: ["0->2","4->5","7"]
     * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
     *
     * Example 2:
     * Input:  [0,2,3,4,6,8,9]
     * Output: ["0","2->4","6","8->9"]
     * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return list;
        }
        if (nums.length == 1) {
            list.add(nums[0] + "");
            return list;
        }

        int start = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                if (i == nums.length - 1) {
                    String end = "";
                    if (start == nums[i]) {
                        end += start;
                    } else {
                        end = start + "->" + nums[i];
                    }
                    list.add(end);
                }
            } else {
                String temp = "";
                if (start == nums[i - 1]) {
                    temp += start;
                } else {
                    temp = start + "->" + nums[i - 1];
                }
                start = nums[i];
                list.add(temp);
                if (i == nums.length - 1) {
                    list.add(nums[i] + "");
                }
            }
        }

        return list;
//        recur(nums, list);
    }

    public static void main(String[] args) {
        Question228 question228 = new Question228();
        int[] nums = {0,2,3,4,6,8,9};
        System.out.println(question228.summaryRanges(nums));
    }
}
