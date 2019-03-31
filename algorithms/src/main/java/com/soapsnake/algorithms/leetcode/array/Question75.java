package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-31 22:05
 */
public class Question75 {


    public void sortColors2(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count0++;
            } else if (nums[i] == 1) {
                count1++;
            } else {
                count2++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < count0) {
                nums[i] = 0;
            } else if (i < count0 + count1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }


    public void sortColors(int[] nums) {

        //思路,一遍数出0,1,2的个数,然后重写数组
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                map.put(0, map.getOrDefault(0, 0) + 1);
            } else if (nums[i] == 1) {
                map.put(1, map.getOrDefault(1, 0) + 1);
            } else {
                map.put(2, map.getOrDefault(2, 0) + 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.get(0) != null && map.get(0) != 0) {
                nums[i] = 0;
                map.put(0, map.get(0) - 1);
                continue;
            }
            if (map.get(1) != null && map.get(1) != 0) {
                nums[i] = 1;
                map.put(1, map.get(1) - 1);
                continue;
            }
            nums[i] = 2;
            map.put(2, map.get(2) - 1);
        }
    }

    public static void main(String[] args) {
        Question75 question75 = new Question75();
        int[] nums = {2, 0, 2, 1, 1, 0};
        question75.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
