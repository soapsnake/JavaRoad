package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created on 2020-04-05
 */
public class Question523 {

    /**
     * A proof sketch:
     * Suppose sum_i represents the running sum starting from index 0 and ending at i,
     * once we find a mod that has been seen, say modk, we have:
     * current one: sum_i = m*k + modk
     * previous one: sum_j = n*k + modk
     * Thus,
     * sum_i - sum_j = (m - n) *k
     * 什么意思了,就是说假如i代表左指针,j代表右指针,如果发现两个sum对k求mod之后余数相同,必然说明这两个数字有关联
     */
    //leetcode523
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>() {{
            put(0, -1);
        }};    //sum  ->  index
        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (k != 0) {
                runningSum %= k;  //这一步到底什么意思了?
            }
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1)
                    return true;
            } else
                map.put(runningSum, i);
        }
        return false;
    }

//    public static void main(String[] args) {
//        Question523 question523 = new Question523();
//        int[] nums = {23, 2, 6, 4, 7};
//        int k = 6;
//        System.out.println(question523.checkSubarraySum(nums, k));
//    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Question523 question523 = new Question523();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(question523.groupAnagrams(strs));
    }

}
