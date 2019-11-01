package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-15 09:59
 */
public class Question686 {

    public static void main(String[] args) {
        Question686 question686 = new Question686();
        String A = "a";
        String B = "aa";
        System.out.println(question686.repeatedStringMatch(A, B));
    }

    public int repeatedStringMatch(String A, String B) {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder(A);
        while (stringBuilder.length() < B.length()) {
            if (!stringBuilder.toString().contains(B)) {
                stringBuilder.append(A);
                i++;
            } else {
                return i;
            }
        }
        if (stringBuilder.toString().contains(B)) {
            return i;
        }
        stringBuilder.append(A);
        i++;
        if (!stringBuilder.toString().contains(B)) {
            return -1;
        } else {
            return i;
        }
    }
}
