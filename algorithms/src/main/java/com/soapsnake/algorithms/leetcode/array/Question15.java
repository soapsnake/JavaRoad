package com.soapsnake.algorithms.leetcode.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 */
class Question15 {

    public static void main(String[] args) {
        Question15 question15 = new Question15();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(question15.threeSum(nums));
    }

    //找出所有可能的组合,这里之所以能用左右指针碰撞算法,是因为结果不要求索引
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) { //防止第一个数字重复
                continue;
            }
            int total = -nums[i];   //剩余两个数字的和要等于total
            List<Integer> sub = null;
            Set<Integer> seconds = new HashSet<>();
            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                if (nums[left] + nums[right] == total) {
                    sub = new ArrayList<>();
                    sub.add(nums[i]); //第一个数字肯定是不一样的
                    if (!seconds.contains(nums[left])) {  //防止第二个数字重复
                        // 只用判断left,因为nums[left]也是单调递增的
                        sub.add(nums[left]);
                        sub.add(nums[right]);
                        res.add(sub);
                        seconds.add(nums[left]);
                    }
                    left++;
                    right--;
                } else if (nums[left] + nums[right] > total) {
                    right--;
                } else if (nums[left] + nums[right] < total) {
                    left++;
                }
            }
        }
        return res;
    }

}
