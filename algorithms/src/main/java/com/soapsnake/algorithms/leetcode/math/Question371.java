package com.soapsnake.algorithms.leetcode.math;

/**
 * @author soapsnake
 * @date 2018/11/22
 */
public class Question371 {

    public static void main(String[] args) {
        Question371 question371 = new Question371();
        System.out.println(question371.getSum(3, 5));
    }

    public int getSum(int a, int b) {
        int c;
        while (b != 0) {
            c = (a & b);
            a = a ^ b;
            b = (c) << 1;
        }
        return a;
    }
}
