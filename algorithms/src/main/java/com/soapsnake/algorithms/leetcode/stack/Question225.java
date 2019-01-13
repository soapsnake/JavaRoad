package com.soapsnake.algorithms.leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-13 18:24
 */
public class Question225 {

    /**
     * 队列实现栈,top()接口实现的有点问题
     */
    static class MyStack {

        Queue<Integer> q1;
        Queue<Integer> q2;
        Queue<Integer> pushQ;
        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
            pushQ = q1;  //初始时q1是入栈队列
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            this.pushQ.add(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            Queue<Integer> popQ = pushQ == q1 ? q2 : q1;

            int i = pushQ.size();
            while (i > 1) {
                Integer temp = pushQ.poll();
                popQ.add(temp);
                i--;
            }
            Integer target = pushQ.poll();
            this.pushQ = popQ;
            return target;
        }

        /**
         * Get the top element.
         */
        public int top() {
            LinkedList<Integer> list = (LinkedList<Integer>) this.pushQ;
            return list.get(list.size() - 1);
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);

        System.out.println(myStack.pop());
        myStack.push(5);
        System.out.println(myStack.pop());

        System.out.println(myStack.pop());

        System.out.println(myStack.top());
    }
}
