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

        int begin = 0;
        int end = row_num * col_num - 1;  //如果是3 x 3的矩阵,那么end = 8

        while(begin <= end){
            int mid = (begin + end) / 2;   //如果是3 x 3矩阵, 那么 mid = (0 + 8) / 2= 4

            //这道题的精髓就是求这个中间点的坐标 = nums[mid / 列数][mid % 列数]
            int mid_value = matrix[mid/col_num][mid%col_num];  //mid_valud = matrix[4 / 3] [4 % 3] = matrx[1][1]刚好是中心位置

            if( mid_value == target){
                return true;

            }else if(mid_value < target){
                //Should move a bit further, otherwise dead loop.
                begin = mid+1;  //如果中点值比value小,那么左边界 = mid + 1  (目标在右侧)
            }else{
                end = mid-1;  //如果中点值比value大,那么右边界 = mid - 1(目标在左侧)
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
