package com.soapsnake.algorithms.leetcode.stack;

import java.util.Stack;

/**
 * 
 * Created on 2021-02-20
 */
public class Question1249 {

    //leetcode1249
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '(') {
                st.add(i);
            }
            if (sb.charAt(i) == ')') {
                if (!st.empty()) {
                    //这里弹出了左括号的索引,相当于保留了这对左括号和右括号,也就是说这对括号其实是合法的
                    st.pop();
                } else {
                    //这里说明这个右括号没有配对的左括号,是个非法括号,先改成*,到最后会被剔除
                    sb.setCharAt(i, '*');
                }
            }
        }
        while (!st.empty()) {
            //到这里,如果栈里还有左括号,说明这个左括号没有配对的右括号,替换成*,最后会被剔除
            sb.setCharAt(st.pop(), '*');
        }

        //最后剔除所有无法配对的左右括号
        return sb.toString().replaceAll("\\*", "");
    }
}
