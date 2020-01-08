package com.soapsnake.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.Map;


/**
 * @author
 * Created on 2019-12-26
 */
public class Question560 {

    //暴力滑动窗口: 如果两个子数组的差位k,那么大子数组与小数组的差集就是目标子数组
    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int res = 0;
        int sumI = 0;
        int sumJ = 0;
        for (int i = 0; i < nums.length; i++) {
            sumI += nums[i];
            sumJ = 0;
            for (int j = 0; j <= i; j++) {
                if (sumI - sumJ == k) {
                    res += 1;
                }
                sumJ += nums[j];
            }
        }
        return res;
    }

    //缓存法,思路同暴力滑动窗口,只是用缓存来防止重复计算,空间换时间
    public int subarraySum3(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int res = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                res += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

}
