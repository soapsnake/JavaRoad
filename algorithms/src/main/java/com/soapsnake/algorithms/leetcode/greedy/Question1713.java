package com.soapsnake.algorithms.leetcode.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created on 2022-01-07
 */
public class Question1713 {

    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                int idx = map.get(arr[i]);
                int tar = findIndex(list, idx);
                if (tar != list.size()) {
                    list.set(tar, idx);
                } else {
                    list.add(idx);
                }
            }
        }
        return target.length - list.size();
    }

    private int findIndex(List<Integer> list, int target) {
        if (list.size() == 0 || target > list.get(list.size() - 1)) {
            return list.size();
        }
        int left = 0, right = list.size() - 1;
        while (right > left) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else  {
                right = mid;
            }
        }
        return left;
    }
}
