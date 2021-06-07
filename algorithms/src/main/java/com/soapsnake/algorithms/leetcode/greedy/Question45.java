package com.soapsnake.algorithms.leetcode.greedy;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-05-05
 */
public class Question45 {

    public int jump(int[] nums) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }
}
