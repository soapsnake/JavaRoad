package com.soapsnake.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created on 2020-04-07
 */
public class Question525 {

    //这个解法真心牛逼
    //leetcode525
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //把所有的0改成-1
            if (nums[i] == 0)
                nums[i] = -1;
        }

        Map<Integer, Integer> sumToIndex = new HashMap<>(); //sum(from nums[0]) -> index
        sumToIndex.put(0, -1);
        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //再一次出现和为sum意味着从上一次和为sum的索引到本次i索引之间的数字之和刚好为0(0和1配对)
            if (sumToIndex.containsKey(sum)) {
                max = Math.max(max, i - sumToIndex.get(sum)); //最长索引差
            } else {
                sumToIndex.put(sum, i);
            }
        }
        return max;
    }
}
