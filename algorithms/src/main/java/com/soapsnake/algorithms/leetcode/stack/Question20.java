package com.soapsnake.algorithms.leetcode.stack;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 */
class Question20 {

    public static void main(String[] args) {
        Question20 question20 = new Question20();
        System.out.println(question20.isValid("()"));
    }

    //利用栈来解决这个问题
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        if ("".equals(s)) {
            return true;
        }

        for (Character c : s.toCharArray()) {
            if (c.equals('(')) {
                stack.push(')');
            } else if (c.equals('[')) {
                stack.push(']');
            } else if (c.equals('{')) {
                stack.push('}');
            } else if (stack.isEmpty() || !stack.pop().equals(c)) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
