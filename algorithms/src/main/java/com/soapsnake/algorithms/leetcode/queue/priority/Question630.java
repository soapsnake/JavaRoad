package com.soapsnake.algorithms.leetcode.queue.priority;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 
 * Created on 2021-05-02
 */
public class Question630 {

    public int scheduleCourse(int[][] courses) {
        //Sort the courses by their deadlines (Greedy! We have to deal with courses with early deadlines first)
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int time = 0;
        for (int[] c : courses) {
            time += c[0]; // add current course to a priority queue
            pq.add(c[0]);
            if (time > c[1])
                //If time exceeds, drop the previous course which costs the most time.
                //(That must be the best choice!)
                time -= pq.poll();
        }
        return pq.size();
    }
}
