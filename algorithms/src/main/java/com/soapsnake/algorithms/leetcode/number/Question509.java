package com.soapsnake.algorithms.leetcode.number;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-25 13:42
 */
public class Question509 {


    /**
     * 递归方法解斐波拉契数列(自顶向下), 12ms
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), for N > 1.
     */
    Map<Integer, Integer> temp = new HashMap<>();

    public static void main(String[] args) {
        Question509 question509 = new Question509();
        System.out.println(question509.fib(4));
    }

    public int fib(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }

        int res;
        if (temp.get(N) != null) {
            return temp.get(N);
        } else {
            res = fib(N - 1) + fib(N - 2);
            temp.put(N, res);
        }
        return res;
    }

    /**
     * 自底向上解斐波拉契数列(DP),2ms
     *
     * @param N
     * @return
     */
    public int fib2(int N) {
        if (N == 1) {
            return 0;
        }
        int first = 0;
        int second = 1;

        while (N-- > 1) {
            int temp = first + second;
            first = second;
            second = temp;
        }
        return second;
    }
}
