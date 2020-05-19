package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.Stack;

public class Question739 {

    //平方级解法
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length < 1) {
            return null;
        }
        int[] res = new int[T.length];

        for (int i = 0; i < T.length; i++) {
            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    //利用栈
    public int[] dailyTemperatures2(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[T.length];    // index -> range
        for(int i = 0; i < T.length; i++) {
            //在栈不为空 && 第i个元素大于栈顶元素的情况下
            while(!stack.isEmpty() && T[i] > T[stack.peek()]) {
                //弹出栈顶元素,是一个索引
                int idx = stack.pop();
                //ret[该索引] = i 与 idx的差值
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        Question739 question739 = new Question739();
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(question739.dailyTemperatures(T)));
    }
}
