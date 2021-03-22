package com.soapsnake.algorithms.leetcode.math;

public class Question29 {


    /**
     * 不用除号实现除法
     * @param a
     * @param b
     * @return
     */
    public int divide(int a, int b) {
        int sign = 1;
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            sign = -1;
        }
        long realA = Math.abs((long) a);
        long realB = Math.abs((long) b);

        if (realB == 0) {
            return Integer.MAX_VALUE;
        }
        if ((realA == 0) || (realA < realB)) {
            return 0;
        }

        long lans = ldivide(realA, realB);

        int ans;
        if (lans > Integer.MAX_VALUE){ //Handle overflow.
            ans = (sign == 1)? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            ans = (int) (sign * lans);
        }
        return ans;
    }

    private long ldivide(long a, long b) {
        // Recursion exit condition
        if (a < b) {
            return 0;
        }

        //  Find the largest multiple so that (divisor * multiple <= dividend),
        //  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
        //  Think this as a binary search.
        long sum = b;
        long multiple = 1;
        while (sum * 2 <= a) {
            sum *= 2;
            multiple += multiple;
        }
        //Look for additional value for the multiple from the reminder (dividend - sum) recursively.
        return multiple + ldivide(a - sum, b);
    }

    public static void main(String[] args) {
        Question29 question29 = new Question29();
        System.out.println(question29.divide(9, 2));
    }
}
