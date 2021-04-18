package com.soapsnake.algorithms.leetcode.queue.circular;

import java.util.Arrays;
import java.util.Comparator;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 *
 * Created on 2020-06-01
 */
public class Question622 {

    //leetcode622
    class MyCircularQueue {
        final int[] a;
        int front, rear = -1, len = 0;

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            a = new int[k];
        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if (!isFull()) {
                rear = (rear + 1) % a.length;
                a[rear] = value;
                len++;
                return true;
            } else return false;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if (!isEmpty()) {
                front = (front + 1) % a.length;
                len--;
                return true;
            } else return false;
        }

        /** Get the front item from the queue. */
        public int Front() {
            return isEmpty() ? -1 : a[front];
        }

        /** Get the last item from the queue. */
        public int Rear() {
            return isEmpty() ? -1 : a[rear];
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return len == 0;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return len == a.length;
        }
    }

    public void deleteNode(ListNode node) {

    }


    public int[][] kClosest(int[][] points, int K) {
//        Arrays.sort(points, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return 0;
//            }
//        });

        return null;
    }


    public static void main(String[] args) {

    }
}
