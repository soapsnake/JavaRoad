package com.soapsnake.algorithms.leetcode.queue.priority;

import java.util.PriorityQueue;

/**
 * 
 * Created on 2021-05-10
 */
public class Question1354 {

    //leetcode1354
    public boolean isPossible(int[] target) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        long total = 0;
        for (int a : target) {
            total += a;
            pq.add(a);
        }
        while (true) {
            int a = pq.poll();
            total -= a;
            if (a == 1 || total == 1)
                return true;
            if (a < total || total == 0 || a % total == 0)
                return false;
            a %= total;
            total += a;
            pq.add(a);
        }
    }
}
