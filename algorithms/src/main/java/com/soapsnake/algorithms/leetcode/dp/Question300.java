package com.soapsnake.algorithms.leetcode.dp;

public class Question300 {


    //遍历版本dp,dp数组存的是从0 -> i的最大递增子数组的长度,每一次计算dp[i+1]的时候都需要重头算0 -> i+1的最大子数组长度和dp[i]比较取较大的
    //所以这个虽然是使用动态规划,但是复杂性还是比较的高的
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];  //dp数组的长度比原始数组长1个
        dp[0] = 1;
        int maxans = 1;   //最终的最长子数组的长度
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {    //新的第i个数字还是保持递增的,这种情况最大长度直接加1接口
                    maxval = Math.max(maxval, dp[j]);
                }
            }

            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
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

    public static void main(String[] args) {
        Question300 question300 = new Question300();
        int[] nums  = {1,3,6,7,9,4,10,5,6};
        System.out.println(question300.lengthOfLIS(nums));
    }
}
