package com.soapsnake.algorithms.leetcode.math;

/**
 *
 * Created on 2020-01-10
 */
public class Question343 {

    //正整数n,拆分成多个正整数的和,条件1:拆分的数字数量不能小于2,条件2:拆分后的数字乘积要最大
    public int integerBreak(int n) {
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        int product = 1;
        while (n > 4) {
            product *= 3;  //最理想的乘积因子是3,只要比3大就应该考虑进行拆分
            n -= 3;  //所谓的拆分就是减法,因为题目要求是多因子的和
        }
        return product * n;  //到这里n已经是比4小的数字了,没必要再继续拆分
    }
}
