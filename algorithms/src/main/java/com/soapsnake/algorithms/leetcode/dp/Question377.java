package com.soapsnake.algorithms.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-01-17
 */
public class Question377 {

    /**
     * find all arrays can sum to the target,can dup number
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return helper(nums, target);
    }
    int[] dp;
    private int helper(int[] nums, int target) {
        if (dp[target] != -1) {
            return dp[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        dp[target] = res;
        return res;
    }

    //EDIT: The above solution is top-down. How about a bottom-up one?
    public int combinationSum4bottom(int[] nums, int target) {
        int[] comb = new int[target + 1];
        comb[0] = 1;
        for (int i = 1; i < comb.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    comb[i] += comb[i - nums[j]];
                }
            }
        }
        return comb[target];
    }



    public int combinationSum4backtrace(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        List<List<Integer>> res = new ArrayList<>();
        backtrace(nums, target, new ArrayList<>(), res, 0);
        System.out.println("res = " + res);
        return res.size();
    }

    private void backtrace(int[] nums, int target, List<Integer> tmp, List<List<Integer>> res, int start) {
        if (addall(tmp) == target) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        if (addall(tmp) > target) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            backtrace(nums, target, tmp, res, i);
            tmp.remove(tmp.size() - 1);
        }
    }

    private int addall(List<Integer> tmp) {
        int res = 0;
        System.out.println("tmp = " + tmp);
        for (Integer integer : tmp) {
            res += integer;
        }
        return res;
    }

    public static void main(String[] args) {
        Question377 question377 = new Question377();
        int[] nums = {1,2,3};
        int target = 4;
        System.out.println(question377.combinationSum4(nums, target));
    }
}
