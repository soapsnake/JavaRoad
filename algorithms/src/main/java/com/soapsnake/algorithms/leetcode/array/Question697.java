package com.soapsnake.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-30 21:23
 */
public class Question697 {

    /**
     * 1. nums中有重复数字,该重复的数字重复次数记为n
     * 2. 则nums的度记为Max(n)
     * 3. 找出nums的连续子数组中,能够达到Max(n)的最短子数组
     * 4. 输出能找到的最短子数组的长度
     *
     *
     *
     * 1. 找最短子数组,数组是无序的
     * 2. 数组是原数组的一段,数字次序不能乱
     * 3. 数组中必须包含重复次数最多的所有数字,比如数字5重复了3次重复的最多,那么该子数组必须包含这3个5
     *
     *
     * loop only once on array {
     *     record the index of first occureence;  //记录首次出现元素的索引
     *     update number counter;                 //更新数字的计数器
     *     update result;                         //更新最终结果
     * }
     * return result;
     */

    public static void main(String[] args) {
        Question697 question697 = new Question697();
        int[] nums = {1, 3, 2, 2, 3, 1};
        System.out.println(question697.findShortestSubArray(nums));
    }

    /**
     * 1. nums中有重复数字,该重复的数字重复次数记为n
     * * 2. 则nums的度记为Max(n)
     * * 3. 找出nums的连续子数组中,能够达到Max(n)的最短子数组
     * * 4. 输出能找到的最短子数组的长度
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, Integer> firstIndex = new HashMap<>();

        int maxDup = 0;
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            firstIndex.putIfAbsent(nums[i], i); //只记录首次索引
            counter.put(nums[i], counter.getOrDefault(nums[i], 0) + 1); //更新数字出现次数

            if (counter.get(nums[i]) > maxDup) {
                maxDup = counter.get(nums[i]);
                res = i - firstIndex.get(nums[i]) + 1;
            } else if (maxDup == counter.get(nums[i])) {
                res = Math.min(res, i - firstIndex.get(nums[i]) + 1);
            }
        }
        return res;
    }

}
