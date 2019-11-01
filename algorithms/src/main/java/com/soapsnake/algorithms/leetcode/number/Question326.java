package com.soapsnake.algorithms.leetcode.number;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-03 15:55
 */
public class Question326 {

    public static void main(String[] args) {
        Question326 question326 = new Question326();

        System.out.println(question326.isPowerOfThree(9));
    }

    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }

        while (n != 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
