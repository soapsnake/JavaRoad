package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-03 22:55
 */
public class Question54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
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
}
