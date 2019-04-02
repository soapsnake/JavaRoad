package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-02 21:45
 */
public class Question90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums); //backtrac类型题目有时候缺的就是这个sort
        List<List<Integer>> res = new ArrayList<>();
        backTrace(nums ,res, 0, new ArrayList<>());
        return res;
    }

    private void backTrace(int[] nums, List<List<Integer>> res, int start, List<Integer> tmp) {
        List<Integer> dest = new ArrayList<>(tmp);
        if (!res.contains(dest)) {
            res.add(dest);
        }
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            backTrace(nums, res, i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        Question90 question90 = new Question90();
        int[] nums = {1, 2, 2};
        System.out.println(question90.subsetsWithDup(nums));


    }
}
