package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-09 21:08
 */
public class Question22 {

    /**
     * n = 3
     *
     * 结果"((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     */
    public List<String> generateParenthesis(int n) {
        //todo 感觉像是全排列问题,是的,你丫猜对了
        if (n == 0) {
            return new ArrayList<>();
        }
        dfsTree("", 0,0, n);
        return res;
    }

    List<String> res = new ArrayList<>();
    private void dfsTree(String tmp, int open, int close, int n) {
        if (tmp.length() == 2 * n) {
            res.add(tmp);
            return;
        }
        if (open < n) {
            dfsTree(tmp + "(", open + 1, close, n);
        }
        if (open > close) {
            dfsTree(tmp + ")", open, close + 1, n);
        }
    }


    public static void main(String[] args) {
        Question22 question22 = new Question22();
        System.out.println(question22.generateParenthesis(3));
    }
}
