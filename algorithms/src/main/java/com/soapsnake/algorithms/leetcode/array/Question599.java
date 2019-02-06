package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-06 23:16
 */
public class Question599 {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list1.length; i++) {
            map1.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            map2.put(list2[i], i);
        }
        int min = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            if (map2.containsKey(entry.getKey())) {
                if (entry.getValue() + map2.get(entry.getKey()) < min) {
                    min = entry.getValue() + map2.get(entry.getKey());
                    res.clear();
                    res.add(entry.getKey());
                } else if (entry.getValue() + map2.get(entry.getKey()) == min) {
                    res.add(entry.getKey());
                }
            }
        }
        return res.toArray(new String[0]);
    }
}
