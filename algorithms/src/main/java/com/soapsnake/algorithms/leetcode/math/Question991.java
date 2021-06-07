package com.soapsnake.algorithms.leetcode.math;

/**
 * 
 * Created on 2021-02-22
 */
public class Question991 {

    /**
     * Let's say we have X = 2 and Y = 9
     * If we move from X to Y, our approach would be to multiply X while it's less than Y
     * & then decrement it.
     * It would be something like
     * (2 * 2 * 2 * 2) - 1 - 1 - 1 - 1 - 1 - 1 - 1 which is 3 multiplication and 7 subtraction (Total 10 operations)
     * but optimal solution would be somthing like
     * (((((2 * 2) - 1) * 2) - 1) * 2) - 1 which is 3 multiplication and 3 subtraction (Total 6 operations)
     *
     * But for optimal solution, how do decide when to multiply or subtract?
     * Let's try to see the equation:
     * Our optimal solution gives us Y, so it will be something like
     * (((((2 * 2) - 1) * 2) - 1) * 2) - 1 = Y
     * (((((2 * 2) - 1) * 2) - 1) * 2) - 1 = 9
     * (((((2 * 2) - 1) * 2) - 1) * 2) = 9 + 1 = 10
     * ((((2 * 2) - 1) * 2) - 1) = 10 / 2 = 5
     * (((2 * 2) - 1) * 2) = 5 + 1 = 6
     * ((2 * 2) - 1) = 6 / 2 = 3
     * 2 * 2 = 3 + 1 = 4
     * 2 = 4 / 2 = 2 (equals X)
     *
     * When we see this, we see that for Y, we are adding one when it's odd
     * and dividing by 2 when it's even.
     * this takes Y close to Y and hence to our answer
     * 一次你一旦消息处理的过程台城,就会导致消息,专门执行心跳的发哦是哪个,其实重平衡的就是
     */
    public int brokenCalc(int X, int Y) {
        int res = 0;
        while (Y > X) {
            Y = Y % 2 > 0 ? Y + 1 : Y / 2;
            res++;
        }
        return res + X - Y;
    }
}
