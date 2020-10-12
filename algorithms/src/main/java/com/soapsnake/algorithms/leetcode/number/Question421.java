package com.soapsnake.algorithms.leetcode.number;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created on 2020-02-24
 */
public class Question421 {

    //leetcode421
    //找到数组中任意两个数字亦或结果的最大值
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
}
