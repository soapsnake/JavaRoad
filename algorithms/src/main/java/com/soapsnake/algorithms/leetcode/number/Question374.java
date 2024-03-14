package com.soapsnake.algorithms.leetcode.number;


/**
 *
 * Created on 2020-01-14
 */
public class Question374 {

    static class GuessGame {

        //这个接口会返回-1,0,+1
        int guess(int num) {
            return 0;
        }
    }

    //二分查找的迭代版,是通过移动两边的边界而非mid指针实现的!!!!
    static class Solution extends GuessGame {
        public int guessNumber(int n) {
            int left = 1, right = n;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (guess(mid) == 0) {
                    return mid;
                } else if (guess(mid) < 0) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }
    }


}
