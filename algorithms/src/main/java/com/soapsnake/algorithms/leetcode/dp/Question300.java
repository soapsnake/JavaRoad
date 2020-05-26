package com.soapsnake.algorithms.leetcode.dp;

public class Question300 {


    public static void main(String[] args) {
        Question300 question300 = new Question300();
        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(question300.lengthOfLIS(nums));
    }

    //遍历版本dp,dp数组存的是从0 -> i的最大递增子数组的长度,每一次计算dp[i+1]的时候都需要重头算0 -> i+1的最大子数组长度和dp[i]比较取较大的
    //所以这个虽然是使用动态规划,但是复杂性还是比较的高的
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];  //1️⃣.dp数组的含义, i -> 到第i个数字时的最长子串的'长度'
        dp[0] = 1;   //2️⃣. dp数组的初始值, 当数组的长度为1时,那么最长子数组的长度就是1(只有一个数字)
        int maxlen = 1;
        for (int i = 1; i < dp.length; i++) {   //i: 1 -> length
            int maxval = 0;
            for (int j = 0; j < i; j++) {   //j: 0 -> i
                if (nums[i] > nums[j]) {    //只要数组是保持递增的,那就可以更新最大长度
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;   //dp [i] = Math.max(maxval, dp[i - 1]) + 1
            maxlen = Math.max(maxlen, dp[i]);
        }
        return maxlen;
    }

    //递归版本算法
    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        //思路:backtrace,找到所有的递增数组,返回长度最长的
        return backtrac(nums, 0, Integer.MIN_VALUE);
    }

    private Integer backtrac(int[] nums, int curIndex, int prev) {
        if (curIndex == nums.length) {
            return 0;
        }
        int notEnd = 0;
        if (nums[curIndex] > prev) {
            notEnd = 1 + backtrac(nums, curIndex + 1, nums[curIndex]);
        }
        int end = backtrac(nums, curIndex + 1, nums[curIndex]);
        return Math.max(notEnd, end);
    }
}
