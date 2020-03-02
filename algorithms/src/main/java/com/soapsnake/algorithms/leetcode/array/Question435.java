package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-02-29
 */
public class Question435 {

    /**
     * Example 1:
     * Input: [[1,2],[2,3],[3,4],[1,3]]
     * Output: 1
     * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
     * <p>
     * Example 2:
     * Input: [[1,2],[1,2],[1,2]]
     * Output: 2
     * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
     * <p>
     * Example 3:
     * Input: [[1,2],[2,3]]
     * Output: 0
     * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
     */
    //leetcode435
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length== 0) return 0;
        //对右边界进行升序排列
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        //count其实是合法范围的数量
        int k = 0, count = 1;
        for(int i =1; i < intervals.length ; i++){
            //如果后一个范围的左边界大于等于前一个范围的右边界,则认为是找到了一个合法的范围,count++
            if (intervals[i][0] >= intervals[k][1]){
                k = i;   //只有在遇到合法区间的情况下,才移动左边界
                count++;
            }
        }
        return intervals.length - count;  //总数-合法的=产生了重叠的
    }
}


