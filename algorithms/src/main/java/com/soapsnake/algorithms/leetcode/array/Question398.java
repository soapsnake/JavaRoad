package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-01-22
 */
public class Question398 {

    //pick要求从nums中挑出target的索引,nums中数字可能存在重复
    static class Solution {
        private int[] nums;
        private Random random = new Random();
        public Solution(int[] nums) {
            this.nums = nums;
        }

        /**
         * To those who don't understand why it works. Consider the example in the OJ
         * {1,2,3,3,3} with target 3, you want to select 2,3,4 with a probability of 1/3 each.
         *
         * 2 : It's probability of selection is 1 * (1/2) * (2/3) = 1/3
         * 3 : It's probability of selection is (1/2) * (2/3) = 1/3
         * 4 : It's probability of selection is just 1/3
         * So they are each randomly selected.
         */
        public int pick(int target) {
            int result = -1;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != target)
                    continue;
                if (random.nextInt(++count) == 0)
                    result = i;
            }

            return result;
        }

        public int pick2(int target) {
            List<Integer> list = new ArrayList<>();  //index
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    list.add(i);
                }
            }
            return list.get(random.nextInt(list.size()));
        }
    }
}
