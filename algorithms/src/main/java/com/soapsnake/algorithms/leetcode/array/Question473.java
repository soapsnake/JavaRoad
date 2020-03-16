package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

public class Question473 {

    //leetcode473
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //题目的隐含条件:必须使用到数组中的所有元素,不会多也不会少
        if (sum % 4 != 0)
            return false;
        //这里的排序是为了优化运算的,对正确性无影响
        Arrays.sort(nums);
        //降序,因为nums是int[]不能在sort时使用比较器,但是为什么要降序了?
        reverse(nums);

        //其实就是backtrace算法,暴力枚举
        return dfs(nums, new int[4], 0, sum / 4);
    }

    private boolean dfs(int[] nums, int[] sums, int index, int target) {
        if (index == nums.length) {
            //target始终没有变过
            if (sums[0] == target && sums[1] == target && sums[2] == target) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (sums[i] + nums[index] > target) continue;
            sums[i] += nums[index];
            if (dfs(nums, sums, index + 1, target)) return true;
            sums[i] -= nums[index]; //backtrace的回退
        }

        return false;
    }

    private void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
