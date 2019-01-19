package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-19 00:30
 */
public class Question482 {

    public String licenseKeyFormatting(String S, int K) {

        StringBuilder sb = new StringBuilder(S);
        int segment = 0;
        int i = 0;
        while (sb.charAt(i) != ' ') {
            if (Character.isDigit(sb.charAt(i)) || Character.isLetter(sb.charAt(i))) {
                segment++;
            } else {
//                sb.replace(i, '');
            }

            if (segment == K) {
                sb.insert(i, '-');
                segment = 0;
            }
            i++;
        }
        return sb.toString();
    }
}
