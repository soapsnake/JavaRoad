package com.soapsnake.algorithms.leetcode.matrix;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-29 23:32
 */
public class Question73 {

    /**
     * Input:
     * [
     * [1,1,1],
     * [1,0,1],
     * [1,1,1]
     * ]
     * Output:
     * [
     * [1,0,1],
     * [0,0,0],
     * [1,0,1]
     * ]
     */
    public void setZeroes(int[][] matrix) {
        //如果matirx[i][j] == 0 ,那么所有matirx[i][...]都为0,所有matrix[....][j]都为0

        boolean fr = false; //0出现在首行
        boolean fc = false; //0出现在首列

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        fr = true;
                    }
                    if (j == 0) {
                        fc = true;
                    }
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {  //注意这里都是从1开始的
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
                if (matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (fr) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (fc) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][0] = 0;
            }
        }
    }
}
