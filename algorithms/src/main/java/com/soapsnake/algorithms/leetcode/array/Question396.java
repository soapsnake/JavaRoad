package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 *
 * Created on 2020-01-21
 */
public class Question396 {

    public int maxRotateFunction(int[] A) {
        int n = A.length;
        int F = 0;
        int sum = 0;
        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            F += i * A[i];
            sum += A[i];
        }
        maxSum = F;
        for (int i = n - 1; i > 0; i--) {
            F = F + sum - n * A[i];
            maxSum = Math.max(F, maxSum);
        }
        return maxSum;
    }

    /**
     * 傻逼版平方级解法
     */
    private int[] rotateArr(int[] a, int k) {
        //A = [4, 3, 2, 6]  ---0--->   [4, 3, 2, 6]
        //A = [4, 3, 2, 6]  ---1--->   [6, 4, 3, 2]  b[0] = a[3], b[1] = a[0], b2 = a1, b3 = a2  k = 1
        //A = [4, 3, 2, 6]  ---2--->   [2, 6, 4, 3]  b0 = a2, b1=a3, b2=a0,b3=a1  k=2, len = 4
        //A = [4, 3, 2, 6]  ---3--->   [3, 2, 6, 4]
        int[] b = new int[a.length];
        System.arraycopy(a, a.length - k, b, 0, k);
        System.arraycopy(a, 0, b, k, a.length - k);
        return b;
    }

    public static void main(String[] args) {
        Question396 question396 = new Question396();
        int[] a = {4, 3, 2, 6};
        int k = 1;
        System.out.println(Arrays.toString(question396.rotateArr(a, k)));
        k = 2;
        System.out.println(Arrays.toString(question396.rotateArr(a, k)));
        k = 3;
        System.out.println(Arrays.toString(question396.rotateArr(a, k)));

        System.out.println(question396.maxRotateFunction(a));
    }
}
