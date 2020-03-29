package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-03-26
 */
public class Question503 {

    //leetcode503
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // index stack
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                next[stack.pop()] = num;
            if (i < n) stack.push(i);
        }
        return next;
    }
}
