package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-19 00:30
 */
public class Question482 {

    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        for (int i = S.length() - 1; i >= 0; i--)
            if (S.charAt(i) != '-')
                sb.append(sb.length() % (K + 1) == K ? '-' : "").append(S.charAt(i));
        return sb.reverse().toString().toUpperCase();
    }
}
