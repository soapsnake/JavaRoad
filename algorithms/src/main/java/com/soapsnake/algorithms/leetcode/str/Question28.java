package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/9 16:19
 */
public class Question28 {

    public static void main(String[] args) {
        Question28 question28 = new Question28();
        String haystack = "a";
        String needle = "a";
        System.out.println(question28.strStr(haystack, needle));
    }

    public int strStr(String haystack, String needle) {
        int pointer = -1;

        int range = needle.length();
        if (range == 0) {
            return 0;
        }
        if (haystack == null || "".equals(haystack)) {
            return pointer;
        }
        if (haystack.length() < needle.length()) {
            return pointer;
        }
        char[] chars = haystack.toCharArray();
        for (int i = 0; i + range <= haystack.length(); i++) {
            if (chars[i] == needle.toCharArray()[0]) {
                if (haystack.substring(i, i + range).equals(needle)) {  //这里一定要用substring,不然会复杂度过高超时
                    pointer = i;
                    break;
                }
            }
        }
        return pointer;
    }
}
