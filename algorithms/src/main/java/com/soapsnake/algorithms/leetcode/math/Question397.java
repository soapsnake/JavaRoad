package com.soapsnake.algorithms.leetcode.math;

/**
 *
 * Created on 2020-01-22
 */
public class Question397 {

    public int integerReplacement(int n) {
        if (n == Integer.MAX_VALUE) return 32; //n = 2^31-1; 少了这一行就会报错
        return helper(n, 0);
    }

    //递归
    private int helper(int n, int steps) {
        if (n < 1){
            return 0;
        } else if (n == 1){
            return steps;
        } else if (n % 2 == 0) {
            return helper(n / 2, steps + 1);
        } else{
            //注意了这里是steps + 2而不是+1
            return Math.min(helper((n + 1) / 2, steps + 2), helper((n - 1) / 2, steps + 2));
        }
    }

    //描述:n如果是偶数就除2,如果是奇数就变成n/2-1或者n/2+1,问n最少除多少次可以变成1
    //思路:如果n+1后能被4整除的话那还是+1好,除此情况都应该-1
    public int integerReplacement2(int n) {
        if (n == Integer.MAX_VALUE) return 32; //n = 2^31-1;
        int count = 0;
        while (n > 1){
            if (n % 2 == 0) n >>>= 1;
            else{
                if ( (n + 1) % 4 == 0 && (n - 1 != 2) ) n++;
                else n--;
            }
            count++;
        }
        return count;
    }
}