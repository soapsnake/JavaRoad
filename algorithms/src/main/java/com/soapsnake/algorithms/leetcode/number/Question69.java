package com.soapsnake.algorithms.leetcode.number;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-16 00:36
 */
public class Question69 {

    public static void main(String[] args) {
        Question69 question69 = new Question69();
        System.out.println(question69.mySqrt(9));
    }

    //todo 二分查找
    public int mySqrt(int x) {
        if (x <= 0) {
            return 0;
        }
        int left = 0;
        int right = Integer.MAX_VALUE;
        int mid = 0;
        while (true) {
            mid = left + (right - left) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1)) { //mid + 1
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
    }
}
