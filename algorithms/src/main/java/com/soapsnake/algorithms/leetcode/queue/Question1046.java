package com.soapsnake.algorithms.leetcode.queue;

import java.util.PriorityQueue;

public class Question1046 {

    //leetcode1046
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a);
        for (int a : stones)
            pq.offer(a);
        while (pq.size() > 1)
            //每次把优先级队列里面最大的两个数字弹出来,做减法后把其差再塞回队列
            //注意,弹出数字后队列的size会减小,因为每一轮都是弹出两个,塞回去一个
            pq.offer(pq.poll() - pq.poll());
        return pq.poll();
    }
}
