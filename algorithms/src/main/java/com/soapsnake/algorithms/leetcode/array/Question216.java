package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question216 {

    public static void main(String[] args) {
        Question216 question216 = new Question216();
        int k = 3;
        int n = 9;
        System.out.println(question216.combinationSum3(k, n));
    }

    /**
     * Example 1:
     * Input: k = 3, n = 7
     * Output: [[1,2,4]]
     * <p>
     * Example 2:
     * Input: k = 3, n = 9
     * Output: [[1,2,6], [1,3,5], [2,3,4]]
     */
    public List<List<Integer>> combinationSum32(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < nums.length; i++) {
            int sum = n - nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                if ((nums[left] + nums[right]) == sum) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (nums[left] + nums[right] > sum) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    //backtrack
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        this.backtrack(nums, new ArrayList<>(), res, k, 0, n);
        return res;
    }

    private void backtrack(int[] nums, List<Integer> tmp, List<List<Integer>> res, int k, int start, int n) {
        if (tmp.size() == k && n == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            backtrack(nums, tmp, res, k, i + 1, n - nums[i]);
            tmp.remove(tmp.size() - 1);
        }
    }
}
