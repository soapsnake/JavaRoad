package com.soapsnake.algorithms.leetcode.array;

import java.util.List;
import java.util.Stack;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-11
 */
public class Question636 {

    /**
     * Input:
     * n = 2
     * logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
     * Output: [3, 4]
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prevTime = 0;
        for (String log : logs) {
            String[] str = log.split(":");
            int taskId = Integer.parseInt(str[0]);
            String state = str[1];
            int time = Integer.parseInt(str[2]);
            if (!stack.isEmpty()) {
                res[stack.peek()] = time - prevTime;
                stack.add(taskId);
            }
            prevTime = time;
            if (state.equals("start")) {
                stack.add(taskId);
            } else {
                res[stack.pop()]++;
                prevTime++;
            }
        }
        return res;
    }
}
