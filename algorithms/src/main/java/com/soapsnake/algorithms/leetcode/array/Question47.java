package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-17 22:18
 */
public class Question47 {

    public static void main(String[] args) {
        Question47 question47 = new Question47();
        int[] nums = {1, 1, 2};
        System.out.println(question47.permuteUnique(nums));
    }

    /**
     * Input: [1,1,2]
     * Output:
     * [
     * [1,1,2],
     * [1,2,1],
     * [2,1,1]
     * ]
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrace(res, new ArrayList<>(), nums, used);
        return res;
    }

    private void backtrace(List<List<Integer>> res, ArrayList<Integer> tmp, int[] nums, boolean[] used) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            /**
             * 跳过的几种情况:
             * 1. nums[i]在一个回合内已经加过了
             * 3. nums[i] 和 nums[i - 1]相同 并且 nums[i - 1] 没有被添加过 ,对应该例子就是两个1时,第一个1没有被加过
             */
            if (used[i]) {   //如果第i个元素已经使用过,那么跳过
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {  //如果i - 1个元素已经使用过那么也跳过
                continue;
            }

            used[i] = true;
            tmp.add(nums[i]);
            backtrace(res, tmp, nums, used);
            used[i] = false;
            tmp.remove(tmp.size() - 1);  //回退前会把第i个元素置为没添加过
        }
    }


}
