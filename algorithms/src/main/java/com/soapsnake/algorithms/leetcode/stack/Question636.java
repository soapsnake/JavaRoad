package com.soapsnake.algorithms.leetcode.stack;

import java.util.List;
import java.util.Stack;

/**
 *
 * Created on 2020-06-13
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
        for (String cur : logs) {
            String[] strings = cur.split(":");
            int curTaskId = Integer.parseInt(strings[0]);
            String curType = strings[1];
            int curTime = Integer.parseInt(strings[2]);
            if (!stack.isEmpty()) {
                res[stack.peek()] += curTime - prevTime;
            }
            prevTime = curTime;
            if (curType.equals("start")) {
                stack.push(curTaskId);
            } else {
                //最难理解的就是这里为什么要加1
                res[stack.pop()]++;
                prevTime++;
            }
        }
        return res;
    }
}
