package com.soapsnake.algorithms.leetcode.queue;

import java.util.Arrays;
import java.util.Comparator;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-01
 */
public class Question622 {

    class MyCircularQueue {

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {

        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            return false;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            return false;
        }

        /** Get the front item from the queue. */
        public int Front() {
            return 0;
        }

        /** Get the last item from the queue. */
        public int Rear() {
            return 0;
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return false;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return false;
        }
    }

    static class Triople{
        String callName;
        int refreshCache;
        String sql;

        public Triople() {}

        @Override
        public String toString() {
            return "Triople{" +
                    "callName='" + callName + '\'' +
                    ", refreshCache=" + refreshCache +
                    ", sql='" + sql + '\'' +
                    '}';
        }
    }

    public void deleteNode(ListNode node) {

    }


    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return 0;
            }
        });


    }


    public static void main(String[] args) {

    }
}
