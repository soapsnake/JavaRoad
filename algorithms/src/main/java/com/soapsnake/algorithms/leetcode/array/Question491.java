package com.soapsnake.algorithms.leetcode.array;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * Created on 2020-03-21
 */
public class Question491 {

    //leetcode491
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        this.backTrace(new LinkedList<>(), 0, nums, res);
        return res;
    }

    private void backTrace(LinkedList<Integer> list, int index, int[] nums, List<List<Integer>> res) {
        if (list.size() > 1) {
            res.add(new LinkedList<>(list));
        }
        Set<Integer> used = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (used.contains(nums[i]))
                continue;
            if (list.size() == 0 || nums[i] >= list.peekLast()) {
                used.add(nums[i]);
                list.add(nums[i]);
                backTrace(list, i + 1, nums, res);
                list.remove(list.size() - 1);
            }
        }
    }
}
