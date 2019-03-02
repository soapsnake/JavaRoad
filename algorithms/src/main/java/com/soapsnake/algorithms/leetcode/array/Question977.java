package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-02 21:47
 */
public class Question977 {

    public int[] sortedSquares(int[] A) {
        //思路,把所有的负数全变成正的(反正要平方的),然后重新排序,最后求平方(排序整数平方后仍然是有序的)
        //复杂度nlog(n),取决于排序
        int[] res = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                A[i] = - A[i];
            } else {
                break;
            }
        }
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            res[i] = A[i] * A[i];
        }
        return res;
    }
}
