package com.soapsnake.algorithms.leetcode.array;

import java.util.*;

public class Question229 {

    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();


    }

    //傻逼版解法,空间复杂度不符合o(1)的要求
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.getOrDefault(nums[i], 0) > nums.length / 3 && !res.contains(nums[i])) {
                res.add(nums[i]);
            } else {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
        }
        return res;
    }

    //todo 大神版解法,需要搞懂,这个算法好像很有意思
    //https://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int candi1 = nums[0], candi2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
        for (int i = 0; i < len; i++) {   //这个for循环的意义在于找到可能的最多重复次数候选数字candi1和candi2
            if (nums[i] == candi1)
                count1++;
            else if (nums[i] == candi2)
                count2++;
            else if (count1 == 0) {
                candi1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                candi2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == candi1)
                count1++;
            else if (nums[i] == candi2)
                count2++;
        }
        if (count1 > len / 3)
            result.add(candi1);
        if (count2 > len / 3)
            result.add(candi2);
        return result;
    }
}
