package com.soapsnake.algorithms.leetcode.math;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-04-07
 */
public class Question1551 {

    /**
     * The focus is on the difference between the middle element and the first n/2 numbers.
     * So for example:
     * 1	3	5	7	9	11
     * +5	+3	+1	-1	-3	-5
     * As you can see, the sequence (+5, +3, +1) is the same as the sum of the first n/2 numbers (1, 3, 5), just the opposite.
     */
    public int minOperations(int n) {
        // Take care of overflow if n is too large.
        if (n % 2 == 1) {
            //如果n是奇数
            n /= 2;  //那么计算中位数
            return (n * (n + 1));   //其实应该是 n*(n+1) / 2 * 2,到中位数的和的一半
        } else {
            n /= 2;
            return n * n;
        }
    }
}
