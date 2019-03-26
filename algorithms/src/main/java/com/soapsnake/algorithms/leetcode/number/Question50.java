package com.soapsnake.algorithms.leetcode.number;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-21 10:09
 */
public class Question50 {

    public static void main(String[] args) {
        Question50 question50 = new Question50();
        double x = 5;
        int n = 5;
        System.out.println(question50.myPow(x, n));
    }

    /**
     * fast power
     * n%2==0 -> x^n = x^(n/2) * x^(n/2) = (x*x)^(n/2)
     * n%2==1 -> x^n = x*(x^(n/2) * x^(n/2)) = x * (x*x)^(n/2)
     */
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;
        // check data overflow
        if (n == Integer.MIN_VALUE) {
            return (1 / x) * myPow(x, n + 1);
        }
        if (n < 0) {
            return 1 / myPow(x, -n);
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, (n - 1) / 2);
    }
}
