package com.soapsnake.algorithms.leetcode.str;

public class Question647 {

    /**
     * 找寻一个字符串中所有的回文子串的个数
     * 主要思路就是对子字符串进行扩展,如果源字符串是回文串,那么在字符串两端各加一个字符,然后看新字符串是否还是回文串
     */
    int count = 0;
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }
        return count;
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }
}
