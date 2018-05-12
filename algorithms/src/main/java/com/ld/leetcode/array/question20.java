package com.ld.leetcode.array;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 An input string is valid if:
 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Note that an empty string is also considered valid.
 */
public class question20 {

    //利用栈来解决这个问题
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        if (s.equals("")) {
            return true;
        }

        for (Character c : s.toCharArray()) {
            if (c.equals('(')) {
                stack.push(')');
            } else if (c.equals('[')) {
                stack.push(']');
            } else if (c.equals('{')) {
                stack.push('}');
            } else if (stack.isEmpty() || !stack.pop().equals(c)){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        question20 question20 = new question20();
        System.out.println(question20.isValid("()"));
    }
}
