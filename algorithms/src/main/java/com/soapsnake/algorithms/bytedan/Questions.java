package com.soapsnake.algorithms.bytedan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-09
 */
public class Questions {
    //题目:lists中的nums都是有序数组,合并这些数组,要求数组都是有序的
    public List<Integer> mergeList(List<int[]> lists) {
        if (lists == null || lists.size() == 0) {
            return new ArrayList<>();
        }
        int[] curPointers = new int[lists.size()];
        int total = 0;
        for (int[] numbers : lists) {
            total += numbers.length;
        }
        List<Integer> res = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            int cur = this.fetchMin(curPointers, lists);
            res.add(cur);
        }
        return res;
    }

    private int fetchMin(int[] pointers, List<int[]> lists) {
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < lists.size(); i++) {
            int[] curArr = lists.get(i);
            int curPointer = pointers[i];
            int cur = curArr[curPointer];
            if (cur < min) {
                curArr[curPointer]++;
                min = cur;
            }
        }
        return min;
    }
}
