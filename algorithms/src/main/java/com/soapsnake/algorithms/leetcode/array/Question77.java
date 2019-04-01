package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-01 22:46
 */
public class Question77 {

    /**
     * backtrace 傻逼算法,n足够大时算法复杂度过高
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        backtrace(arr, k, new ArrayList<>(), res, 0);
        return res;
    }

    private void backtrace(int[] arr, int k, List<Integer> tmp,List<List<Integer>> res ,int start) {
        if (tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            if (tmp.contains(arr[i])) {
                continue;
            }
            tmp.add(arr[i]);
            backtrace(arr, k, tmp, res, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        Question77 question77 = new Question77();
        int n = 10, k = 7;
        System.out.println(question77.combine(n ,k));
    }
}
