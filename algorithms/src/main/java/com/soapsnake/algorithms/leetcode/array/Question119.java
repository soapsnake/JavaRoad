package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Question119 {


    public List<Integer> getRow(int rowIndex) {
        return this.generate(rowIndex + 1).get(rowIndex);
    }

    /**
     * Could you optimize your algorithm to use only O(k) extra space?
     */
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

    public List<Integer> getRow2(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        if (rowIndex < 0) {
            return list;
        }

        for (int i = 0; i < rowIndex + 1; i++) {
            list.add(0, 1);
            for (int j = 1; j < list.size() - 1; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
        }
        return list;
    }
}
