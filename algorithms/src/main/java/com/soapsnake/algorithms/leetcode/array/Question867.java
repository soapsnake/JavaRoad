package com.soapsnake.algorithms.leetcode.array;


import java.util.Arrays;

//矩阵转置
class Question867 {

    public static void main(String[] args) {
        Question867 question867 = new Question867();

        int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println(Arrays.deepToString(question867.transpose(A)));
    }

    public int[][] transpose(int[][] A) {
        int row = A.length;
        int collum = A[0].length;
        int[][] B = new int[collum][row];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                B[j][i] = A[i][j];
            }
        }
        return B;
    }

}
