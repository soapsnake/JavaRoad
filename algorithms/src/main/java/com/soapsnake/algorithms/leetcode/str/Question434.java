package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-19 00:57
 */
public class Question434 {

    public static void main(String[] args) {
        Question434 question434 = new Question434();
        String s = "Hello, my name is John";
        System.out.println(question434.countSegments(s));
    }

    public int countSegments(String s) {
        String[] strings = s.split(" ");
        int maxCount = 0;
        String maxStr = "";
        for (String s1 : strings) {
            if (s1.length() > maxCount) {
                int trim = 0;
                for (int i = 0; i < s1.length(); i++) {
                    if (!Character.isLetter(s1.charAt(i))) {
                        trim++;
                    }
                }
                maxCount = s1.length() - trim > maxCount ? s1.length() - trim : maxCount;
            }
        }
        return maxCount;
    }

    public int countSegments2(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
                res++;
            }
        return res;
    }
}
