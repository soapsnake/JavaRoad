package com.soapsnake.algorithms.leetcode.greedy;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-05-05
 */
public class Question45 {

    public int jump(int[] nums) {
        int ans = 0;
        // 下一轮可到达的最远位置
        int nextMax = 0;
        // 当前轮可到达的最远位置
        int curMax = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 在当前可到达的最远位置范围内查找下一次可到达的最远位置
            nextMax = Math.max(nextMax, i + nums[i]);  //这句等同于遍历nums[i]找一个最大的值,然后赋值给nextMax
            // 索引到达本轮最远距离时触发下一次jump,同时更新curMax的值
            if (i == curMax) {
                ans++;
                curMax = nextMax;
            }
        }
        return ans;
    }
}
