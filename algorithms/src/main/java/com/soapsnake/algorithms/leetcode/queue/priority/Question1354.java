package com.soapsnake.algorithms.leetcode.queue.priority;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created on 2021-05-10
 */
public class Question1354 {

    public static void main(String[] args) {
        Question1354 question1354 = new Question1354();
        String s = "(()";
        System.out.println(question1354.longestValidParentheses(s));
    }

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

    public int longestValidParentheses(String s) {
        int res = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                for (int j = s.length() - 1; j > i; j--) {
                    if (isValid(s.substring(i, j + 1))) {
                        res = Math.max(res, j - i + 1);
                    }
                }
            }
        }
        return res;
    }

    private boolean isValid(String s) {
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
                if (balance < 0) {
                    return false;
                }
            }
        }
        return balance == 0;
    }

    /**
     * 维护一个栈,如果是(那么放入下标,如果是)那么弹出下标并且计算长度,并和max比较
     * 栈顶永远放的是不合法的)的下标,这意味着该下标是该合法串的左边界,也意味着栈里面实际上永远只有两个元素?????
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek()); //stack里面存的是下标,
                }
            }
        }
        return res;
    }
}
