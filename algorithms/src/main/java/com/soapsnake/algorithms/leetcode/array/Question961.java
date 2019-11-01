package com.soapsnake.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-03 22:04
 */
public class Question961 {

    public int repeatedNTimes(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (map.get(A[i]) == null) {
                map.put(A[i], 1);
            } else {
                map.put(A[i], map.get(A[i]) + 1);
            }
        }

        int maxdup = -1;
        int maxEle = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxdup) {
                maxdup = entry.getValue();
                maxEle = entry.getKey();
            }
        }
        return maxEle;
    }
}
