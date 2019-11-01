package com.soapsnake.algorithms.leetcode.stack;

import java.util.Stack;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-20 13:40
 */
public class Question232 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 11; i++) {
            stack.push(i);
        }

//        for (int i : stack) {
//            System.out.println(i);
//        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }

    /**
     * 用栈实现队列
     * 这个实现有点傻逼,就是查的时候需要倒腾,查完还需要再倒腾回来
     */
    class MyQueue {
        private Stack<Integer> pushStack = new Stack<>();
        private Stack<Integer> getStack = new Stack<>();
        private int count = 0;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {

        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            pushStack.push(x);
            count++;
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            while (!pushStack.isEmpty()) {
                getStack.push(pushStack.pop());
            }
            int target = getStack.pop();
            while (!getStack.isEmpty()) {
                pushStack.push(getStack.pop());
            }
            return target;
        }

        /**
         * Get the front element.
         */
        public int peek() {
            while (!pushStack.isEmpty()) {
                getStack.push(pushStack.pop());
            }
            int target = getStack.peek();
            while (!getStack.isEmpty()) {
                pushStack.push(getStack.pop());
            }
            return target;
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return pushStack.isEmpty();
        }
    }
}
