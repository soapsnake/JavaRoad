package com.soapsnake.algorithms.leetcode.number;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-16 01:02
 */
public class Question231 {

    /**
     * 思路:不断除以2,如果除的过程中不能整除,那就false,直到为1
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }

        while (n != 1) {
            if (n  % 2 != 0 && n != 1) { //技巧全在这了,n % 2在前防止不能整除
                return false;
            }
            n = n / 2;
            if (n == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Question231 question231 = new Question231();
        System.out.println(question231.isPowerOfTwo(3));

    }
}
