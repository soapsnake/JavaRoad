package com.soapsnake.algorithms.leetcode.matrix;

import java.util.TreeMap;

/**
 *
 * Created on 2020-03-02
 */
public class Question436 {

    //leetcode436
    //题目要求返回的是紧挨着第i个interval右侧的第一个interval的索引,不是数量
    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();  //start -> origin index
        int[] res = new int[intervals.length];
        //这个for循环负责存储每个interval的左区间和index映射
        //treemap在进行put的时候就会调用compare,意味着数据进入map后就已经是按照key排过序的
        for (int i = 0; i < intervals.length; i++)
            map.put(intervals[i][0], i);

        for (int i = 0; i < intervals.length; i++) {
            //ceilingKey会返回大于等于所给key的最小key值
            //这个key就是第一个大于上一个区间右区间的第一个interval的左区间,有点拗口
            Integer key = map.ceilingKey(intervals[i][1]);

            //为什么感觉res[i]的值是一个索引而不是数量了
            res[i] = key != null ? map.get(key) : -1;
        }
        return res;
    }
}
