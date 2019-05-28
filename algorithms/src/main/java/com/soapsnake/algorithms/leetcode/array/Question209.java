package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Question209 {

    //不是特别懂啊这个
    public int minSubArrayLen(int s, int[] nums) {
        int i = 0, j = 0, total = 0, min = Integer.MAX_VALUE;
        while (j < nums.length) {
            total += nums[j++];
            while (total >= s) {   //这里没有用 == 很精髓
                min = Math.min(min, j - i);
                total -= nums[i++];   //这里会向右移动i指针
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 解法1:暴力枚举,复杂度n²
     */
    public int minSubArrayLen1(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //递归
        List<Integer> arr = new ArrayList<>();
        arr.add(0);
        dfs(nums, s, nums[0], arr);
        return min;
    }

    int min = Integer.MAX_VALUE;
    private void dfs(int[] nums, int s, int preTotal, List<Integer> tmp) {
        int pre = tmp.get(tmp.size() - 1);
        int cur = pre + 1;
        if (cur >= nums.length) {
            return;
        }
        if (preTotal + nums[cur] == s) {
            tmp.add(cur);
            min = Math.min(min, tmp.size());
        } else if (preTotal + nums[cur] > s) {
            List<Integer> arr = new ArrayList<>();
            arr.add(tmp.get(0) + 1); //第一个元素的下一个元素作为开始,因为已经不可能出正确结果了
            dfs(nums, s, nums[cur], arr);
        } else {
            tmp.add(cur);
            dfs(nums, s, preTotal + nums[cur], tmp);
        }
    }

    public static void main(String[] args) {
        Question209 question209 = new Question209();
        int[] nums = {2,3,1,2,4,3};
        int s = 7;
        System.out.println(question209.minSubArrayLen1(s, nums));
    }
}
