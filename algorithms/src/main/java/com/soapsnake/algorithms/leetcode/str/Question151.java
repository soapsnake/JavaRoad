package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-03-27 00:08
 */
public class Question151 {

    //充分说明了使用stringbuilder比字符串+效率有多高,使用前22ms,使用后6ms
    public String reverseWords(String s) {
        String[] parts = s.trim().split("\\s+"); //正则
        StringBuilder out = new StringBuilder();
        for (int i = parts.length - 1; i > 0; i--) {
            out.append(parts[i]).append(" ");
        }
        return out + parts[0];
    }

    public static void main(String[] args) {
        Question151 question151 = new Question151();
        String string = "the sky is blue";
        System.out.println(question151.reverseWords(string));
    }
}
