package com.soapsnake.algorithms.leetcode.str;

import java.util.Stack;

public class Question227 {

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int num = 0;
        char preSign = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= s.length(); i++) {
            if (i < s.length() && Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
            } else if (i == s.length() || !Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') {
                if (preSign == '+') {
                    stack.push(num);
                } else if (preSign == '-') {
                    stack.push(-num);
                } else if (preSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (preSign == '/') {
                    stack.push(stack.pop() / num);
                }
                if (i != s.length())
                    preSign = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        for (int i : stack) {
            res += i;
        }
        return res;
    }

    public static void main(String[] args) {
        Question227 question227 = new Question227();
        String s  = "3+5/2";
        System.out.println(question227.calculate(s));
    }
}
