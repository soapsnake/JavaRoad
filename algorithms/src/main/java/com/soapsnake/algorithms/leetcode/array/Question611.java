package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created on 2020-05-31
 */
public class Question611 {

    //leetcode611
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0, n = nums.length;
        for (int i = n - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    //如果这对right和left是满足的,那么由于排过序,所有left指针右侧的数字也都是满足的
                    count += right - left;
                    right--;
                } else
                    left++;
            }
        }
        return count;
    }

        //backtrace, 复杂度过高
    public int triangleNumber1(int[] nums) {
        //nums数组中可以组成三角形的数量
        //思路,backtrace暴力算
        List<List<Integer>> res = new ArrayList<>();
        this.backTrace(res, nums, new ArrayList<>(), 0);
        return res.size();
    }

    private void backTrace(List<List<Integer>> res, int[] nums, List<Integer> tmp, int start) {
        if (isValid(tmp)) {
            res.add(tmp);
        }
        for (int i = start; i < nums.length; i++) {
            tmp.add(nums[i]);
            backTrace(res, nums, tmp, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

    private boolean isValid(List<Integer> tmp) {
        if (tmp.size() != 3) {
            return false;
        }
        return tmp.get(0) + tmp.get(1) > tmp.get(2)
                && tmp.get(1) + tmp.get(2) > tmp.get(0)
                && tmp.get(0) + tmp.get(2) > tmp.get(1);
    }

    public static void main(String[] args) {
        Question611 question611 = new Question611();
        int[] nums = {2,2,3,4};
        System.out.println(question611.triangleNumber(nums));

    }
}
