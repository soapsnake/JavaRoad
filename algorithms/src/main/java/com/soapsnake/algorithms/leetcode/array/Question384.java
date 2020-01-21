package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.collections4.SplitMapUtils;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-01-20
 */
public class Question384 {

    static class Solution {
        int[] source;
        Random random = new Random();
        public Solution(int[] nums) {
            source = nums;
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return source;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            //洗牌算法
            int[] res = source.clone();
            for (int i = 0; i < res.length; i++) {
                int index = random.nextInt(i + 1);
                swapByIndex(res, index, i);
            }
            return res;
        }

        public void swapByIndex(int[] nums, int index, int i) {
            int temp = nums[index];
            nums[index] = nums[i];
            nums[i] = temp;
        }

        public static void main(String[] args) {
            int[] nums = {1,2,3};
            Solution solution = new Solution(nums);
            System.out.println(Arrays.toString(solution.shuffle()));
            System.out.println(Arrays.toString(solution.reset()));
            System.out.println(Arrays.toString(solution.shuffle()));
        }
    }
}
