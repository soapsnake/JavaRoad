package com.soapsnake.algorithms.leetcode.number;

import java.util.Stack;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-02-20
 */
public class Question150 {

    /**
     * Example 1:
     * Input: ["2", "1", "+", "3", "*"]
     * Output: 9
     * Explanation: ((2 + 1) * 3) = 9
     * <p>
     * <p>
     * Example 2:
     * Input: ["4", "13", "5", "/", "+"]
     * Output: 6
     * Explanation: (4 + (13 / 5)) = 6
     * <p>
     * <p>
     * Example 3:
     * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
     * Output: 22
     * Explanation:
     * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     */
    //leetcode150
    public int evalRPN(String[] tokens) {
        if (tokens == null) {
            return 0;
        }
        int a, b;
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            switch (s) {
                case "+":
                    stack.add(stack.pop() + stack.pop());
                    break;
                case "/":
                    b = stack.pop();
                    a = stack.pop();
                    stack.add(a / b);
                    break;
                case "*":
                    stack.add(stack.pop() * stack.pop());
                    break;
                case "-":
                    b = stack.pop();
                    a = stack.pop();
                    stack.add(a - b);
                    break;
                default:
                    stack.add(Integer.parseInt(s));
                    break;
            }
        }
        return stack.pop();
    }
}
