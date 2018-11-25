package com.soapsnake.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author soapsnake
 * @date 2018/11/23
 */
public class Question349 {

    //o(n²)
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] lagger;
        int[] smaller;
        //两个数组的交集
        if (nums1.length >= nums2.length) {
            lagger = nums1;
            smaller = nums2;
        } else {
            lagger = nums2;
            smaller = nums1;
        }
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < smaller.length; i++) {
            for (int j = 0; j < lagger.length; j++) {
                if (lagger[j] == smaller[i]) {
                    res.add(lagger[j]);
                }
            }
        }

        int[] resu = new int[res.size()];
        int k = 0;
        for (int i : res) {
            resu[k++] = i;
        }
    return resu;
    }

    //O(n)
    public int[] intersection2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], 1);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (map.get(nums2[i]) != null) {
                map.put(nums2[i], 2);
            }
        }
        Set<Integer> res = new HashSet<>();
        for (Map.Entry entry : map.entrySet()) {
            if ((int)entry.getValue() != 1) {
                res.add((int)entry.getKey());
            }
        }

        int[] resu = new int[res.size()];
        int k = 0;
        for (int i : res) {
            resu[k++] = i;
        }
        return resu;
    }

    //todo 对长数组进行排序,然后使用二分查找
}
