package com.ld.leetcode.array;

import java.util.Arrays;

/**
 * @author soapsnake
 * @date 2018/11/6
 */
public class Question566 {

    //两次循环 + 额外数组,(本人的)弱智解法
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int sourceRow = nums.length;
        int sourceCol = nums[0].length;
        if (sourceCol * sourceRow != r * c) {
            return nums;
        }

        int[][] sourceRes = new int[sourceCol * sourceRow][1];
        int k = 0;
        for (int i = 0; i < sourceRow; i++) {
            for (int j = 0; j < sourceCol; j++) {
                int temp  = nums[i][j];
                sourceRes[k++][0] = temp;
            }
        }
        if (c == 1) {
            return sourceRes;
        }
        int x = 0;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = sourceRes[x++][0];
            }
        }
        return res;
    }

    //todo 单循环 + 无额外空间解法,提示:进位算法
    public int[][] matrixReshape2(int[][] nums, int r, int c) {

        return null;
    }


        public static void main(String[] args) {
        Question566 question566 = new Question566();
        int[][] asn = {{1,2}, {3,4}};
        System.out.println(Arrays.deepToString(question566.matrixReshape(asn, 1, 4)));
    }
}
