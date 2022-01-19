package com.soapsnake.algorithms.cruel;

/**
 *
 * Created on 2022-01-19
 *
 * 前缀和
 */
public class AboutPresum {

    //1685
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int sum = 0;
        int[] preSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            preSum[i] = sum;
        }
        //计算每个数的差绝对值之和
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int leftTotal = (i + 1) * nums[i] - preSum[i];
            int rightTotal = preSum[nums.length - 1] - preSum[i] - nums[i] * (nums.length - 1 - i);
            res[i] = leftTotal + rightTotal;
        }
        return res;
    }
}
