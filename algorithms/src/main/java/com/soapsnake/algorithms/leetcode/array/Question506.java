package com.soapsnake.algorithms.leetcode.array;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-23 09:44
 */
public class Question506 {

    public String[] findRelativeRanks(int[] nums) {
        String[] res = new String[nums.length];
        if (res.length == 0) {
            return res;
        }
        //值 -> 原始索引
        Map<Integer, Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;   //大 -> 小
            }
        });
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);   //值 -> 原始索引
        }

        int rank = 1; //顺序
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int item =  entry.getKey();
            int indexOfItem = entry.getValue();
            String str = "";
            if (rank == 1) {
                str = "Gold Medal";
            }else if (rank == 2) {
                str = "Silver Medal";
            }else if (rank == 3) {
                str = "Bronze Medal";
            } else {
                str = rank + "";
            }
            res[indexOfItem] = str;   //res数组的赋值不是按顺讯的,而是按nums数组中的值从大 -> 小的顺序进行赋值
            rank++;
        }
        return res;
    }
}
