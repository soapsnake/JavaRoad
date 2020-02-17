package com.soapsnake.algorithms.leetcode.number;

import java.util.Stack;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-02-17
 */
public class Question402 {

    //leetcode402
    public String removeKdigits(String num, int k) {
        int len = num.length();
        //corner case
        if (k == len)
            return "0";

        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < num.length()) {
            //数字想要入栈有一个条件:新数字如果比当前栈顶大,那么入栈该数字,如果新数字比当前栈顶小,那么弹出当前栈顶
            //一直到新数字比栈顶大为止,其实是一种贪心的思想,每次都挑比较大的数字放入
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }

        // 防止每数字都相同的情况
        while (k > 0) {
            stack.pop();
            k--;
        }

        //数字从栈顶到栈底是依次减小的,所以要进行翻转
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        sb.reverse();

        //如果头部是0,那么需要清除掉
        while (sb.length() > 1 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);
        return sb.toString();
    }
}
