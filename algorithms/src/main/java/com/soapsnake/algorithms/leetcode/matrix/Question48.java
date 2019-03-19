package com.soapsnake.algorithms.leetcode.matrix;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-18 10:35
 */
public class Question48 {

    /**
     * Given input matrix =
     * [
     *   [1,2,3],               [7,8,9]               [7,4,1]
     *   [4,5,6],   =>行变换     [4,5,6]    =>转置     [8,5,2]    图像顺时针旋转算法,逆时针了?
     *   [7,8,9]                [1,2,3]               [9,6,3]
     * ],
     *
     * rotate the input matrix in-place such that it becomes:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     */
    public void rotate(int[][] matrix) {

        if (matrix.length == 0) {
            return;
        }

        //行交换
        this.reverseRow(matrix);

        //转置
        this.transPose(matrix);

    }

    //二阶矩阵转置标准写法
    private int[][] transPose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        Question48 question48 = new Question48();

        int[][] matrix = {{1,2,3,4},{4,5,6,7},{7,8,9,10},{8,9,10,11}};
        System.out.println(Arrays.deepToString(question48.reverseRow(matrix)));
    }

    //二阶矩阵行上下翻转标准细写法
    private int[][] reverseRow(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int len = matrix[i].length;
                int high = 0;
                int low = len - 1;
                while (high < low) {
                       int temp =  matrix[high][i];
                       matrix[high][i] = matrix[low][i];
                       matrix[low][i] = temp;
                       high++;
                       low--;
                }
            }
        return matrix;
    }
}
