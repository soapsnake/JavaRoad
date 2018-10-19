package com.ld.leetcode.array;


import java.util.Arrays;

//矩阵转置
public class Question867 {

    public int[][] transpose(int[][] A) {
        //todo 只适用于正方形矩阵,不适用于长方形矩阵
        for (int i=0;i<A.length;i++){
            for (int j=0;j<A[i].length;j++){
                int temp = A[i][j];
                A[i][j] = A[j][i];
                A[j][i] = temp;
            }
        }

        return A;
    }

    public static void main(String[] args) {
        Question867 question867 = new Question867();

        int[][] A = {{1,2,3},{4,5,6},{7,8,9}};

        System.out.println(Arrays.deepToString(question867.transpose(A)));
    }

}
