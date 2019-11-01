package com.soapsnake.algorithms.leetcode.number;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-18 00:05
 */
public class Question367 {

    public static void main(String[] args) {
        Question367 question367 = new Question367();
        System.out.println(question367.isPerfectSquare(16));
    }

    public boolean isPerfectSquare(int num) {
        if (num == 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }

        int left = 0;
        int right = num;
        int mid = left + (right - left) / 2;
        return findMid(left, right, num);
    }

    private boolean findMid(int left, int right, int num) {
        if (right < left) {
            return false;
        }
        int mid = left + (right - left) / 2;
        if (mid < 0) {
            return false;
        }
        if (mid * mid == num) {
            return true;
        }
        if (mid > num / mid) { //注意这里不能用mid * mid > num
            return findMid(left, mid - 1, num);
        }
        if (mid < num / mid) {
            return findMid(mid + 1, right, num);
        }
        return false;
    }
}
