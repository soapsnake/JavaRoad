package com.soapsnake.algorithms.leetcode.array;

import com.soapsnake.algorithms.alib.DPtester;

/**
 *
 * Created on 2020-02-23
 */
public class Question413 {

    //leetcode413
    //数组的等差数列子数组的个数
    public int numberOfArithmeticSlices(int[] A) {
        int curr = 0, total = 0;
        for (int i = 2; i < A.length; i++)
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                //发现 i-2,i-1,i是一个等差数列
                curr += 1;
                total += curr;  //感觉这里有斐波拉契数列的意思
            } else {
                curr = 0;
            }
        return total;
    }

    public int numberOfArithmeticSlices1(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        //dp数组含义:已i结尾的等差数组的数量
        int[] dp = new int[nums.length + 1];
        dp[2] = 0;   //为什么是dp[2] 而不是dp[0]:数组长度要大于等于3
        int res = 0;
        for (int i = 2; i < nums.length; i++) {  //i = 2
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }
        return res;
    }

    }
