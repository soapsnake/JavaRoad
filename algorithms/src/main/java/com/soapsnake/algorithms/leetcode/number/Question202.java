package com.soapsnake.algorithms.leetcode.number;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-13 10:26
 */
public class Question202 {

    /**
     * 数字的各个位不断求平方和,看最终值是否为1
     */

    public static void main(String[] args) {
        Question202 question202 = new Question202();
        System.out.println(301 % 10);
        System.out.println(301 / 10);
        System.out.println(question202.isHappy(19));
    }

    Set<Integer> cache = new HashSet<>();
    public boolean isHappy(int n) {
        //求余法求各个位的值,感觉是递归问题
        int len = (n + "").length();
        if (!cache.add(n)) {  //出现循环了,如果不加这个则递归无法终止
            return false;
        }
        if (n == Math.pow(10, len - 1)) {
            return true;
        }
        int temp = 0;
        while (n > 0) {
            int wei = n % 10;
            temp += wei * wei;
            n = n / 10;
        }
        return isHappy(temp);
    }
}
