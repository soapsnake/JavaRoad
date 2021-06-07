package com.soapsnake.algorithms.leetcode.math;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-29 19:26
 */
public class Question204 {

    public static void main(String[] args) {
        Question204 question204 = new Question204();
        int n = 10;
        System.out.println(question204.countPrimes(n));    //return 4
    }

    //todo 计算小于n的质数的数量(正数)
    public int countPrimes(int n) {
        /**
         *  思路:什么是素数? 不能被除1和自身整除的数
         *  那么如果两个数的成绩时
         */
        if(n <=1 ) return 0;

        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;

        for(int i = 2; i < Math.sqrt(n); i++){
            if(!notPrime[i]){
                for(int j = 2; j*i < n; j++){
                    notPrime[i*j] = true;
                }
            }
        }

        int count = 0;
        for(int i = 2; i< notPrime.length; i++){
            if(!notPrime[i]) count++;
        }
        return count;

    }
}
