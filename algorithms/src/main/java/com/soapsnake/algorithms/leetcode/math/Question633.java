package com.soapsnake.algorithms.leetcode.math;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-07 23:53
 */
public class Question633 {
    public boolean judgeSquareSum(int c) {
        if (c == 0) {
            return true;
        }
        int left = 0;
        int right = (int) Math.sqrt(c);
        //左右指针
        while (right >= left) {
            int dest = left * left + right * right;
            if (dest > c) {
                right--;
            } else if (dest < c) {
                left++;
            } else {
                return true; //找到符合的结果
            }
        }
        return false;
    }
}
