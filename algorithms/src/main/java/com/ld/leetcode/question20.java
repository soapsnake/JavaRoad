package com.ld.leetcode;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 An input string is valid if:
 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Note that an empty string is also considered valid.
 */
public class question20 {

    public boolean isValid(String s) {

        return true;
    }

    public static void main(String[] args) {
        question20 question20 = new question20();
        System.out.println(question20.isValid("(){}[]"));
    }
}
