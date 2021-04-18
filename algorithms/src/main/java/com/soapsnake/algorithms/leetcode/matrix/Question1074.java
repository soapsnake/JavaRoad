package com.soapsnake.algorithms.leetcode.matrix;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-04-18
 */
public class Question1074 {

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int count = 0;
        int line = matrix.length;
        int column = matrix[0].length + 1;
        int[][] sum = new int[line][column];

        for (int i = 0; i < sum.length; i++){
            for (int j = 1; j < sum[0].length; j++){
                //是从第1列开始算起的,所以sum矩阵第0列都还是0
                sum[i][j] = sum[i][j - 1] + matrix[i][j - 1];
            }
        }


        for (int start = 0; start < column; start++){
            for (int end = start + 1; end < column; end++ ){

                int sumOfSubMatrix = 0;
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                for(int l = 0; l < line; l++){  //一列一列往后移动
                    sumOfSubMatrix += sum[l][end] - sum[l][start];
                    if (map.containsKey(sumOfSubMatrix - target))
                        count += map.get(sumOfSubMatrix - target);
                    map.put(sumOfSubMatrix, map.getOrDefault(sumOfSubMatrix, 0) + 1);

                }
            }
        }

        return count;
    }
}
