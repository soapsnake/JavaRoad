package com.ld.leetcode.number;

import java.util.Stack;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 Example 1:
 Input: 123
 Output: 321
 */
class Question7 {

    public int reverse(String x) {
        if (null == x)
            return 0;
        Stack<Character> stack = new Stack<>();
        boolean neg = false;
        for (char c : x.toCharArray()){
            if (c == '-'){
                neg = true;
                continue;
            }
            stack.push(c);
        }

        String dest = "";
        final int up = stack.size();
        for (int i=1;i<=up;i++)
            dest += stack.pop();
        if (neg){
            return -Integer.parseInt(dest);
        }
        return Integer.parseInt(dest);
    }

    public static void main(String[] args) {
        Question7 question7 = new Question7();
        System.out.println(question7.reverse("123"));
        System.out.println(question7.reverse("-123"));
        System.out.println(question7.reverse("-890"));
        System.out.println(question7.reverse("9646324351"));

    }
}
