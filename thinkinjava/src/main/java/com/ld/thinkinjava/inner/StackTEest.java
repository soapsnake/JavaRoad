package com.ld.thinkinjava.inner;

import java.util.Stack;

/**
 * Created by liudun on 2017/6/29.
 */
public class StackTEest {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();


        stack.push("this");
        stack.push("is");
        stack.push("a");
        stack.push("fucking");
        stack.push("shit");
        stack.push("pro");

        for (String string : stack) {
            System.out.println(string);

        }

        System.out.println(stack.pop());

        System.out.println(stack);

        System.out.println(stack.pop());

        System.out.println(stack);

        System.out.println(stack.pop());

        System.out.println(stack);

        System.out.println(stack.pop());

        System.out.println(stack);

    }
}
