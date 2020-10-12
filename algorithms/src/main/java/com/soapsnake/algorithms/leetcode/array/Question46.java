package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-15 12:02
 */
public class Question46 {


    public static void main(String[] args) {
        Question46 question46 = new Question46();
        int[] nums = {1, 2, 3};
        System.out.println(question46.permute(nums));
    }

    /**
     * Input: [1,2,3]
     * Output:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfsTree(res, new ArrayList<>(), nums);
        return res;
    }

    private void dfsTree(List<List<Integer>> res, List<Integer> tmp, int[] nums) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (tmp.contains(nums[i])) {
                continue;
            }
            tmp.add(nums[i]);
            dfsTree(res, tmp, nums);
            tmp.remove(tmp.size() - 1);
        }
    }
}
