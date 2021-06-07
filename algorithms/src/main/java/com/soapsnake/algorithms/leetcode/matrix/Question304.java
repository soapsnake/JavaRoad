package com.soapsnake.algorithms.leetcode.matrix;

public class Question304 {

    static class NumMatrix2 {

        private int[][] matrix;

        public NumMatrix2(int[][] matrix) {
            this.matrix = matrix;
        }

        //O(m * n) 的复杂度
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

    //leetcode304,初始化时直接算好,那么查的时候复杂度会大大降低
    static class NumMatrix {
        private int[][] matrix;
        private int[][] dp;

        NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            this.matrix = matrix;
            int len = matrix.length;
            int col = matrix[0].length;
            dp = new int[len][col + 1];    //dp多出来一列
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < col; j++) {
                    dp[i][j + 1] = dp[i][j] + matrix[i][j];  //自底向上
                }
            }
        }

        //o(m)复杂度
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res = 0;
            for (int i = row1; i <= row2; i++) {
                res += dp[i][col2 + 1] - dp[i][col1];
            }
            return res;
        }
    }
}
