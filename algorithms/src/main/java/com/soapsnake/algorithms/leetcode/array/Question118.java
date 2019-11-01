package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-18 10:06
 */
public class Question118 {

    public static void main(String[] args) {
        Question118 question118 = new Question118();
        int n = 4;
        System.out.println(question118.generate(n));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> prevRow = null;
        for (int i = 0; i < numRows; i++) {
            if (i > 0) {
                prevRow = res.get(i - 1);
            }
            List<Integer> currentRow = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    currentRow.add(j, 1);
                } else {
                    currentRow.add(j, prevRow.get(j) + prevRow.get(j - 1));
                }
            }
            res.add(currentRow);
        }
        return res;
    }
}
