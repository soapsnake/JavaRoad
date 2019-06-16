package com.soapsnake.algorithms.leetcode.matrix;

public class Question304 {

    static class NumMatrix {

        private int[][] matrix;
        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        //m * n 的复杂度
        public int sumRegion(int row1, int col1, int row2, int col2) {
            // i: row1 -> row2
            //j: col1 -> col2
            int res = 0;
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    res += matrix[i][j];
                }
            }
            return res;
        }
    }
}
