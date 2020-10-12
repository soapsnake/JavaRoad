package com.soapsnake.algorithms.leetcode.str;

import java.util.HashMap;

public class Question166 {

    /**
     * Example 1:
     * Input: numerator = 1, denominator = 2
     * Output: "0.5"
     * <p>
     * Example 2:
     * Input: numerator = 2, denominator = 1
     * Output: "2"
     * <p>
     * Example 3:
     * Input: numerator = 2, denominator = 3
     * Output: "0.(6)"
     */
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();
        //最前面的正负号
        String sign = (numerator < 0 && denominator < 0 || numerator == 0) ? "" : "-";
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        result.append(sign);
        result.append(num / den);      //这个是小数点前面的数字
        long remainder = num % den;   //余数
        if (remainder == 0)
            return result.toString();  //如果没有小数点后的位数那么直接返回
        result.append(".");
        HashMap<Long, Integer> hashMap = new HashMap<>();
        while (!hashMap.containsKey(remainder)) {
            hashMap.put(remainder, result.length());
            result.append(10 * remainder / den);
            remainder = 10 * remainder % den;
        }
        int index = hashMap.get(remainder);
        result.insert(index, "(");
        result.append(")");
        return result.toString().replace("(0)", "");
    }
}
