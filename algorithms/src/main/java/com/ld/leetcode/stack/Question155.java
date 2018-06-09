package com.ld.leetcode.stack;

import java.util.Comparator;
import java.util.Stack;



public class Question155 {

    /**
     * 思路,用两个栈:普通栈实现普通的先进后出的功能
     * 最小栈只存放最小的元素:
     *              压入时,如果该值比最小栈中元素小,那就更新
     *              弹栈时,如果弹出了最小栈中的元素,那就把普通栈中的最小元素再次压到最小栈里面
     * 代码实现正确,就是复杂度有点高
     */
     static class MinStack {
         Stack<Integer> normal;
         Stack<Integer> zuixiao;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            normal = new Stack<>();
            zuixiao = new Stack<>();
        }

        public void push(int x) {
            this.normal.push(x);
            if (zuixiao.isEmpty()){
                zuixiao.push(x);
            }
            if (x < zuixiao.peek()) {
                zuixiao.pop();
                zuixiao.push(x);
            }
        }

        public void pop() {
            if (!normal.isEmpty()){
                int temp = normal.pop();
                if (temp == zuixiao.peek()){
                    zuixiao.pop();
                    Object[] res = normal.stream().sorted(Comparator.comparingInt(a -> a)).toArray();
                    if(res.length >0) zuixiao.push((int)res[0]);
                }
            }
        }

        public int top() {
            if (!normal.isEmpty()) return normal.peek();
            else return 0;
        }

        public int getMin() {
            if (!zuixiao.isEmpty())
                return zuixiao.peek();
            else return 0;
        }
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(2);
        stack.push(0);
        stack.push(3);
        stack.push(0);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
    }

    /**
     * 什么叫做为达目的不择手段....
     * head节点不仅保存了自己的值,还保存了最小值.....
     */
    static class ListVersionMinStack {
        private Node head;

        public void push(int x) {
            if(head == null)
                head = new Node(x, x);
            else
                head = new Node(x, Math.min(x, head.min), head);
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }

        private class Node {
            int val;
            int min;
            Node next;

            private Node(int val, int min) {
                this(val, min, null);
            }

            private Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }
}
