package com.soapsnake.algorithms.leetcode.str;

import java.util.LinkedList;

public class Question71 {

    /**
     * 题目描述: 简化unix的绝对路径,去除路径中多余的., .., ./等符号
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder("/");
        LinkedList<String> stack = new LinkedList<>();
        /**
         * 1. ".." 操作符,进行回退
         * 2. "a, b" 文件目录,直接入栈
         * 3. 其他,"."等不做任何操作
         */
        for (String s : path.split("/")) {
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();   //..操作符代表回退,这里直接删掉刚入栈的元素(类似pop??)
                }
            } else if (!s.equals("") && !s.equals("."))  //合法目录
                stack.add(s);
        }
        for(String s: stack){
            sb.append(s + "/");
        }
        if (!stack.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
