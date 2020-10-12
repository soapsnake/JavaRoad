package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-24 19:11
 */
public class Question60 {

    public static void main(String[] args) {
        Question60 question60 = new Question60();
        int n = 9;
        int k = 331987;
        long start = System.currentTimeMillis();
        System.out.println(question60.getPermutation(n, k));
        System.out.println("时间 = " + (System.currentTimeMillis() - start));


        System.out.println(Long.MAX_VALUE);

    }

    /**
     * Example 1:
     * Input: n = 3, k = 3
     * Output: "213"
     * <p>
     * <p>
     * Example 2:
     * Input: n = 4, k = 9
     * Output: "2314"
     */
    public String getPermutation(int n, int k) {

        if (n == 0) {
            return "";
        }
        if (n == 1) {
            return "1";
        }
        List<String> res = new LinkedList<>();
        backTrace(n, res, new ArrayList<>());
        return res.get(k - 1);
    }

    private void backTrace(int init, List<String> res, List<Integer> tmp) {
        if (tmp.size() == init) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i : tmp) {
                stringBuilder.append(i);
            }
            res.add(stringBuilder.toString());
            return;
        }
        for (int i = 1; i <= init; i++) {
            if (tmp.contains(i)) {
                continue;
            }
            tmp.add(i);
            backTrace(init, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
