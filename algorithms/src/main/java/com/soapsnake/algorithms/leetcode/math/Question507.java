package com.soapsnake.algorithms.leetcode.math;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-24 13:45
 */
public class Question507 {

    public static void main(String[] args) {
        Question507 question507 = new Question507();
        System.out.println(question507.checkPerfectNumber(28));   //应该为true
    }

    /**
     * 题目描述:判断一个数字,它的所有除数相加是否等于该数本身(包含1,但是不包含该值自身)
     *
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        if (num == 1 || num == 0) {
            return false;
        }
        int res = 1;
        for (int i = 2; i < num / 2 + 1; i++) {
            if (num % i == 0)
                res += i;
        }
        return res == num;
    }
}
