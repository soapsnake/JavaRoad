package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-16 17:34
 */
public class Question40 {


    /**
     *Input: candidates = [10,1,2,7,6,1,5], target = 8,
     * A solution set is:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        this.backTrace(res, new ArrayList<>(), candidates,target, 0);
        return res;

    }

    private void backTrace(List<List<Integer>> res, List<Integer> tmp, int[] candidates, int remain, int start) {
        if (remain == 0) {
            res.add(new ArrayList<>(tmp));
        } else if (remain > 0) {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                tmp.add(candidates[i]);
                backTrace(res, tmp, candidates, remain - candidates[i], i + 1); //这里写错了i + 1导致耗费大量时间
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private boolean valid(List<Integer> tmp, int target) {
        int res = 0;
        for (int i : tmp) {
            res += i;
        }
        return res == target;
    }


    public static void main(String[] args) {
        Question40 question40 = new Question40();
        int[] nums = {10,1,2,7,6,1,5};
        int tar = 8;
        System.out.println(question40.combinationSum2(nums, tar));
    }

}
