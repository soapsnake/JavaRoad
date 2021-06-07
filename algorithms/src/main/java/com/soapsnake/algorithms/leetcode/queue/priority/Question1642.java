package com.soapsnake.algorithms.leetcode.queue.priority;

import java.util.PriorityQueue;

/**
 * 
 * Created on 2021-04-27
 */
public class Question1642 {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //默认是小顶堆
        for (int i = 0; i < heights.length - 1; i++) {
            int d = heights[i + 1] - heights[i];

            if (d > 0)
                //如果下一栋楼更高,高差存入pq
                pq.add(d);
            if (pq.size() > ladders)
                //优先使用梯子,多于梯子的,优先使用最小高度的砖头
                bricks -= pq.poll();
            if (bricks < 0)
                //砖头用完
                return i;
        }
        return heights.length - 1;
    }
}
