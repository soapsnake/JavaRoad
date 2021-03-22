package com.soapsnake.algorithms.leetcode.math;

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
     * 求x的n次方
     * fast power
     * n%2==0 -> x^n = x^(n/2) * x^(n/2) = (x*x)^(n/2)
     * n%2==1 -> x^n = x*(x^(n/2) * x^(n/2)) = x * (x*x)^(n/2)
     */
    //leetcode50
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;
        // check data overflow
        if (n == Integer.MIN_VALUE) {
            //n为负无穷大
            return (1 / x) * myPow(x, n + 1);
        }
        if (n < 0) {  //如果n小于0
            return 1 / myPow(x, -n);
        }

        //如果n能够被2整除(2的n次方)  x的n次方 = (x²) n/2次方
        //如果n不能够被2整除(非2的n次方)  x的n次方 = x * (x²)(n - 1)/2次方
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, (n - 1) / 2);
    }
}
