package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-04 21:01
 */
public class Question39 {

    public static void main(String[] args) {
        Question39 question39 = new Question39();
        int[] nums = {2, 3, 6, 7};
        int tartget = 7;
        System.out.println(question39.combinationSum(nums, tartget));
    }

    /**
     * Input: candidates = [2,3,5], target = 8,
     * A solution set is:
     * [
     * [2,2,2,2],
     * [2,3,3],
     * [3,5]
     * ]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfsTree(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void dfsTree2(List<List<Integer>> res, ArrayList<Integer> tmp, int[] candidates, int remain, int start) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            res.add(new ArrayList<>(tmp));
        } else {
            for (int i = start; i < candidates.length; i++) {
                tmp.add(candidates[i]);
                dfsTree2(res, tmp, candidates, remain - candidates[i], i);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private void dfsTree(List<List<Integer>> res, ArrayList<Integer> tmp, int[] candidates, int remain, int start) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                tmp.add(candidates[i]);
                dfsTree(res, tmp, candidates, remain - candidates[i], i);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    //旋转数组二分查找
    public boolean search2(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target;
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[0] <= nums[mid]) {
                //0 -> mid一定是升序的
                if (target >= nums[0] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                //mid -> len - 1一定是升序的
                if (target > nums[mid] && target <= nums[nums.length - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }
}
