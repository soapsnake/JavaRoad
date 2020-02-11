package com.soapsnake.algorithms.leetcode.number;

import sun.security.util.Length;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-02-10
 */
public class Question400 {

    //leetcode400
    public int findNthDigit(int n) {
        int start = 1, len = 1;
        long count = 9;   //这个count必须是long类型,否则报错
        while (n > len * count ) {
            n -= count * len;   //9x1, 90x2,900x3
            len++;
            count *= 10;
            start *= 10;
        }
        //start只管保持长度和n一致,最后n位置的数字要经过处理,类似10000000000xx这样
        start += (n - 1) / len;
        String num = start + "";
        System.out.println("num=" + num + " len= " + len);
        return Character.getNumericValue(num.charAt((n - 1) % len));
    }

    public static void main(String[] args) {
        Question400 question400 = new Question400();
        System.out.println(question400.findNthDigit(11));
    }
}
