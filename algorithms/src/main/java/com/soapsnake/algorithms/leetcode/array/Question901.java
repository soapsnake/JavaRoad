package com.soapsnake.algorithms.leetcode.array;

import java.util.Stack;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-19
 */
public class Question901 {

    //leetcode901
    class StockSpanner {
        private Stack<int[]> stack;
        public StockSpanner() {
            stack = new Stack<>();
        }
        public int next(int price) {
            int res = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= price)
                res += stack.pop()[1];
            stack.push(new int[]{price, res});
            return res;
        }
    }

}
