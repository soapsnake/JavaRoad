package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-04 21:01
 */
public class Question39 {

    /**
     * Input: candidates = [2,3,5], target = 8,
     * A solution set is:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfsTree(res, new ArrayList<>(), candidates, target, 0);
        return  res;
    }

    private void dfsTree(List<List<Integer>> res, ArrayList<Integer> tmp, int[] candidates, int remain, int start) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            /**
             *  最关键的就是这个new ArrayList,由于tmp后续会改变,所以如果不copy出来一个新对象,
             *  那么tmp中的值会一直变,res也会跟着变,导致最后结果不准确
             */
            res.add(new ArrayList<>(tmp));
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                tmp.add(candidates[i]);
                dfsTree(res, tmp, candidates, remain - candidates[i], i); //这里为什么用i
                tmp.remove(tmp.size() - 1);  //这个remove操作完成了回退操作,非常的神奇
            }
        }
    }

    public static void main(String[] args) {
        Question39 question39  = new Question39();
        int[] nums = {2,3,6,7};
        int tartget = 7;
        System.out.println(question39.combinationSum(nums, tartget));
    }
}
