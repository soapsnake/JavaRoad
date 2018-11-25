package com.soapsnake.algorithms.leetcode.array;

import java.util.LinkedList;
import java.util.List;

/**
 * @author soapsnake
 * @date 2018/11/3
 */
class Question682 {

    public int calPoints(String[] ops) {
        int res = 0;
        int current = 0;
        List<Integer> validOpr = new LinkedList<>();
        for (int i =0; i < ops.length; i++) {
            if ("+".equals(ops[i])) {
                if (validOpr.size() == 1) {
                    current = ((LinkedList<Integer>) validOpr).getLast();
                }
                if (validOpr.size() >= 2){
                    current = validOpr.get(validOpr.size() - 1) + validOpr.get(validOpr.size() - 2);
                }
                res += current;
                validOpr.add(current);
                continue;
            }
            if ("C".equals(ops[i])) {
                res -= ((LinkedList<Integer>) validOpr).getLast();
               ((LinkedList<Integer>) validOpr).removeLast();
                continue;
            }
            if ("D".equals(ops[i])) {
                current =  2 * ((LinkedList<Integer>) validOpr).getLast();
                validOpr.add(current);
                res += current;
                continue;
            }
            res += Integer.valueOf(ops[i]);
            validOpr.add(Integer.valueOf(ops[i]));
        }
        return res;
    }

    public static void main(String[] args) {
        Question682 question682 = new Question682();
        String[] strings = {"5","-2","4","C","D","9","+","+"};
        System.out.println(question682.calPoints(strings));
    }
}
