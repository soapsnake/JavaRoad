package com.soapsnake.algorithms.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-03 22:55
 */
public class Question54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) {
            return res;
        }

        int rowBegin = 0;
        int rowEnd = matrix.length - 1;  //列长
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;  //行长

        //循环总的限制条件就是下标不能超过最外层的索引(行和列)
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j++) {
                res.add(matrix[rowBegin][j]);  // 光标第一行从左往右走
            }
            rowBegin++;   //行数递增

            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j++) {
                res.add(matrix[j][colEnd]);   //光标最右边一列往下走
            }
            colEnd--;   //列数递减(到达最底层后往左移动)

            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j--) {
                    res.add(matrix[rowEnd][j]);  //光标从右往左走
                }
            }
            rowEnd--;  //光标到达最左之后往上移动

            if (colBegin <= colEnd) {    //不能和之前遍历过得行重叠
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j--) {
                    res.add(matrix[j][colBegin]);   //光标往上移动
                }
            }
            colBegin++;   //列递增,准备启动第二次的顺时针循环(内圈)
        }
        return res;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) {
            return res;
        }
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd ; i++) {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++;
            for (int i = rowBegin; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i >= colBegin ; i--) {
                    res.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin ; i--) {
                    res.add(matrix[i][colBegin]);
                }
            }
            colBegin++;
        }
        return res;
    }




    public List<Integer> spiralOrder3(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        while (rowEnd >= rowBegin && colEnd >= colBegin) {
            for (int i = colBegin; i <= rowEnd; i++) {
                res.add(matrix[rowBegin][i]);         //往右
            }
            rowBegin++; //这里光标往下走一行,是防止重复取到最后一个元素的

            for (int i = rowBegin; i <= rowEnd; i++) { //往下
                res.add(matrix[i][colEnd]);
            }
            colEnd--; //光标左移,防止重复
            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i >= colBegin ; i--) {
                    res.add(matrix[rowEnd][i]);        //往左
                }
            }
            rowEnd--; //光标上移,防重复
            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    res.add(matrix[i][colBegin]);     //往上
                }
            }
            colBegin++; //列右移,防重复
        }
        return res;
    }


















}
