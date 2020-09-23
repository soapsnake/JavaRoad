package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
    //leetcode229
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        //题目要求的是出现n/3次数的数字,那么数组中其实最多只会有两个数字能够出现这么多次
        int candi1 = nums[0];  //候选数字1
        int candi2 = nums[0];  //候选数字2
        int count1 = 0;  //候选数字1的出现次数
        int count2 = 0;  //候选数字2的出现次数
        for (int i = 0; i < nums.length; i++) {   //这个for循环的意义在于找到可能的最多重复次数候选数字candi1和candi2
            if (nums[i] == candi1)
                count1++;
            else if (nums[i] == candi2)
                count2++;
            else if (count1 == 0) {
                //如果候选1的出现次数减到了0,那么意味着candi1的当前值已经不可能是候选值了
                candi1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                //同理
                candi2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        //为什么又要算一次了?加了这个相当于2o(n)了
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == candi1)
                count1++;
            else if (nums[i] == candi2)
                count2++;
        }
        if (count1 > nums.length / 3)
            result.add(candi1);
        if (count2 > nums.length / 3)
            result.add(candi2);
        return result;
    }
}
