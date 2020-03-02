package com.soapsnake.algorithms.leetcode.matrix;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-03-02
 */
public class Question436 {

    //leetcode436
    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++)
            map.put(intervals[i][0], i);
        for (int i = 0; i < intervals.length; i++) {
            Integer key = map.ceilingKey(intervals[i][1]);
            res[i] = key != null ? map.get(key) : -1;
        }
        return res;
    }
}
