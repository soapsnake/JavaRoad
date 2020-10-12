package com.soapsnake.algorithms.leetcode.matrix;

public class Question240 {

    /**
     * Consider the following matrix:
     * <p>
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * Given target = 5, return true.
     * Given target = 20, return false.
     * 矩阵的每一行和每一列都是排序的
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        //思路:从矩阵的右上角开始搜索,如果比目标大,那么只能列数左移,如果比目标小,那么只能行数下移,想想为啥
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int col = matrix[0].length - 1;
        int row = 0;
        while (col >= 0 && row <= matrix.length) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;

    }
}
