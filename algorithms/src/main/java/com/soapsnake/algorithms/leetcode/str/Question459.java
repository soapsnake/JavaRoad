package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-20 16:15
 */
public class Question459 {

    public boolean repeatedSubstringPattern(String s) {
        if (s.length() == 1) {
            return false;
        }

        List<Integer> range = new ArrayList<>(); //可能的原子子串长度
        for (int i = 1; i < s.length(); i++) {
            if (i * 2 > s.length()) { //长度超过了一半,不可能再有子串了
                break;
            }
            if (s.charAt(i) == s.charAt(0)) {
                range.add(i);
            }
        }

        for (Integer i : range) { //会验证每一种可能的子串,是否有必要了??
            String sub = s.substring(0, i);
            int sublen = sub.length();
            boolean match = true;
            for (int j = i; j < s.length(); j += sublen) {
                String sub1;
                if (j + sublen > s.length()) {
                    sub1 = s.substring(j);
                } else {
                    sub1 = s.substring(j, j + sublen);
                }
                if (!sub1.equals(sub)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Question459 question459 = new Question459();
        String s = "aabaaba";
        System.out.println(question459.repeatedSubstringPattern(s));
    }
}
