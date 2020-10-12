package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-11 10:21
 */
public class Question830 {

    public static void main(String[] args) {
        Question830 question830 = new Question830();
        String S = "aaa";
        System.out.println(question830.largeGroupPositions(S));
    }

    //todo 双指针法也可以解这道题,复杂度和解法一差不了多少
    public List<List<Integer>> largeGroupPositions2(String S) {
        return null;
    }

    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();
        int dup = 1;
        int start = -1;
        for (int i = 0; i < S.length(); i++) {
            if (i != 0 && S.charAt(i) == S.charAt(i - 1)) {
                start = start >= 0 ? start : i - 1;
                dup++;
                if (i == S.length() - 1) {
                    if (dup >= 3) {
                        List<Integer> list = new ArrayList<>();
                        list.add(start);
                        list.add(S.length() - 1);
                        res.add(list);
                    }
                }
            } else {
                if (dup >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(i - 1);
                    res.add(list);
                }
                start = -1;
                dup = 1;
            }
        }
        return res;
    }
}
