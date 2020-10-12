package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
class Question1 {
    public static void main(String[] args) {
        int[] ques = new int[]{3, 3};
        Question1 question1 = new Question1();
        ArrayUtils.printArr(question1.twoSum6(ques, 6));

//        for (int i = 1;i<20;i++) {
//            System.out.println(i % 10);
//        }
    }


    public int[] twoSum4(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();  // num -> index
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            if (nums[low] + nums[high] == target) {
                res[0] = map.get(nums[low]);
                res[1] = map.get(nums[high]);
                return res;
            } else if (nums[low] + nums[high] > target) {
                high--;
            } else {
                low++;
            }

        }
        return null;
    }

    public int[] twoSum5(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return nums;
    }

    public int[] twoSum6(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                res[0] = i;
                res[1] = map.get(target - nums[i]);
                return res;
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }


    //解法1:暴力枚举
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j > 0; j--) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    //解法2:字典保存法,这个思路可以用来处理递归计算中的重复计算问题
    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> temp = new HashMap<>();   //key = val, value = index
        for (int i = 0; i < nums.length; i++) {
            if (temp.get(target - nums[i]) != null) {
                res[0] = i;
                res[1] = temp.get(target - nums[i]);
            }
            temp.put(nums[i], i);
        }
        return res;
    }

    //解法3:左右指针碰撞算法!!!!这题不行,因为nums不是排序的,但是如果对其进行排序,会破坏其原始指针
    //这里用了个map来保存值 -> 索引的映射,想法是好的,但是一旦有重复的值就歇逼了,所以还是不行
    public int[] twoSum3(int[] nums, int target) {
        int[] res = new int[2];
        int left = 0;
        int right = nums.length - 1;
        Map<Integer, Integer> map = new TreeMap<>(); //值 -> 索引
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        Arrays.sort(nums);
        for (; right > left; ) {
            if (nums[left] + nums[right] == target) {
                res[0] = map.get(nums[left]);
                res[1] = map.get(nums[right]);
                break;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else if (nums[left] + nums[right] < target) {
                left++;
            }
        }
        return res;
    }
}
