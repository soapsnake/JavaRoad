package com.soapsnake.algorithms.leetcode.matrix;

public class Question59 {

    public static int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        int left = 0, top = 0;
        int right = n - 1, down = n - 1;
        int count = 1;
        while (left <= right) {
            for (int j = left; j <= right; j++) {
                //第一行从左往右赋值
                ret[top][j] = count++;
            }
            top++;  //防重复赋值
            for (int i = top; i <= down; i++) {
                //最后一列从上往下赋值
                ret[i][right] = count++;
            }
            right--;
            for (int j = right; j >= left; j--) {
                //最后一行从右往左赋值
                ret[down][j] = count++;
            }
            down--;
                //第一列从下往上赋值
            for (int i = down; i >= top; i--) {
                ret[i][left] = count++;
            }
            left++;
        }
        return ret;
    }
}
