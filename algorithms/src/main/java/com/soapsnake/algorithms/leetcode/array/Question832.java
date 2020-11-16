package com.soapsnake.algorithms.leetcode.array;

/**
 * Input: [[1,1,0],[1,0,1],[0,0,0]]
 * Output: [[1,0,0],[0,1,0],[1,1,1]]
 * Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].   //先进行横向翻转
 * Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]                 //然后0和1互换
 */
class Question832 {
    public static void main(String[] args) {
        Question832 question832 = new Question832();
        int[][] a = {{1, 1, 0, 1}, {1, 0, 1, 1}, {0, 0, 0, 1}};
        question832.flipAndInvertImage(a);
    }

    //todo 平方级别算法,有没有复杂度更低的算法?
    //leetcode832
    public int[][] flipAndInvertImage(int[][] A) {

        //行反转
        for (int i = 0; i < A.length; i++) {
            int len = A[i].length;
            for (int j = 0; j < (A[i].length + 1) / 2; j++) {
                int temp = A[i][j];
                A[i][j] = A[i][len - 1 - j];
                A[i][len - 1 - j] = temp;
            }
        }

        //0转1
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] == 1) {
                    A[i][j] = 0;
                } else {
                    A[i][j] = 1;
                }
            }
        }

        return A;
    }
}
