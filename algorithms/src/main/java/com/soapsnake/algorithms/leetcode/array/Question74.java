package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-30 12:03
 */
public class Question74 {

    /**
     * Input:  二维数组的二分查找
     * matrix = [
     *   [1,   3,  5,  7],
     *   [10, 11, 16, 20],
     *   [23, 30, 34, 50]
     * ]
     * target = 3
     * Output: true
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int row_num = matrix.length;
        int col_num = matrix[0].length;

        int begin = 0, end = row_num * col_num - 1;

        while(begin <= end){
            int mid = (begin + end) / 2;
            int mid_value = matrix[mid/col_num][mid%col_num];

            if( mid_value == target){
                return true;

            }else if(mid_value < target){
                //Should move a bit further, otherwise dead loop.
                begin = mid+1;
            }else{
                end = mid-1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Question74 question74 = new Question74();
        int[][] matxi = {{1,   3,  5,  7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        int tar = 100;
        System.out.println(question74.searchMatrix(matxi, tar));
    }
}
