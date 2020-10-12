package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-26 21:57
 */
public class Question532 {

    public static void main(String[] args) {
        Question532 question532 = new Question532();
        int[] nus = {3, 1, 4, 1, 5};
        int k = 2;
        System.out.println(question532.findPairs2(nus, k));
    }

    //用双指针再做一次,
    //todo
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);  //数字 -> 数字出现次数
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                //count how many elements in the array that appear more than twice.
                if (entry.getValue() >= 2) {
                    count++;
                }
            } else {
                if (map.containsKey(entry.getKey() + k)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int findPairs2(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int res = 0;

        while (right >= 0 && left < nums.length) {
            if (Math.abs(nums[right] - nums[left]) == k) {
                res++;
                right--;
                left++;
            } else if (Math.abs(nums[right] - nums[left]) > k) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }


}
