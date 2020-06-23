package com.soapsnake.algorithms.leetcode.deque;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-18
 */
public class Question641 {

    /**
     * 所谓的双端队列,就是头进尾出那就是普通队列,但是头进头出或者尾进尾出就是栈
     */
     class MyCircularDeque {
        int size;      //当前容量
        int max;      //最大限制容量
        DoubleListNode head;
        DoubleListNode tail;
        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int max) {
            head = new DoubleListNode(-1);
            tail = new DoubleListNode(-1);
            head.pre = tail;
            tail.next = head;
            this.max = max;
            this.size = 0;
        }

        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            if (size == max)
                return false;
            DoubleListNode node = new DoubleListNode(value);
            node.next = head;
            node.pre = head.pre;
            head.pre.next = node;
            head.pre = node;
            size++;
            return true;
        }

        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            if (size == max)
                return false;
            DoubleListNode node = new DoubleListNode(value);
            node.next = tail.next;
            tail.next.pre = node;
            tail.next = node;
            node.pre = tail;
            size++;
            return true;
        }

        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if (size == 0)
                return false;
            head.pre.pre.next = head;
            head.pre = head.pre.pre;
            size--;
            return true;
        }

        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if (size == 0)
                return false;
            tail.next.next.pre = tail;
            tail.next = tail.next.next;
            size--;
            return true;
        }

        /** Get the front item from the deque. */
        public int getFront() {
            return head.pre.val;
        }

        /** Get the last item from the deque. */
        public int getRear() {
            return tail.next.val;
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return size == 0;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return size == max;
        }
    }

    class DoubleListNode {
        DoubleListNode pre;
        DoubleListNode next;
        int val;
        public DoubleListNode(int val) {
            this.val = val;
        }
    }
}
