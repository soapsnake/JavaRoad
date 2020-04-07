package com.soapsnake.algorithms.leetcode.array;

/**
 *
 * Created on 2020-03-21
 */
public class Question495 {

    //leetcode495
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0 || duration == 0) {
            return 0;
        }
        int result = 0, start = timeSeries[0], end = timeSeries[0] + duration;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] > end) {
                //如果右区间大于范围,说明没有重叠,这时候更新左指针和右指针
                result += end - start;
                start = timeSeries[i];
            }
            //如果右区间小于范围,说明发生重叠,这种情况只更新右指针,左指针不变
            end = timeSeries[i] + duration;
        }
        result += end - start;
        return result;
    }
}
