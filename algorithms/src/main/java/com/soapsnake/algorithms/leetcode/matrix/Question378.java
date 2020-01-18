package com.soapsnake.algorithms.leetcode.matrix;

public class Question378 {

    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi) 这个+1???
        while(lo < hi) {  //这个可不是索引范围了,而是值范围,在值范围上做二分搜索
            int mid = lo + (hi - lo) / 2;   //mid是中值
            int count = 0,  j = matrix[0].length - 1;   //j为列索引
            for(int i = 0; i < matrix.length; i++) {
                while(j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);  //count和索引相关
            }
            if(count < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        Question378 question378 = new Question378();
        int[][] mattix = {{1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 8;
        System.out.println(question378.kthSmallest(mattix, k));
    }
}
