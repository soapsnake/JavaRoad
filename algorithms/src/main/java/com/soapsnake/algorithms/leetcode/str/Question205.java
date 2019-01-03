package com.soapsnake.algorithms.leetcode.str;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-03 20:56
 */
public class Question205 {

    public boolean isIsomorphic(String s, String t) {

        Set<Character> sTemp = new HashSet<>();
        Set<Character> tTemp = new HashSet<>();
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (!sTemp.add(charS[i])) {
                charS[i] = '0';
            }
            if (!tTemp.add(charT[i])) {
                charT[i] = '0';
            }
        }

        for (int i = 0; i < charS.length; i++) {
            if (charS[i] == '0') {
                if (charT[i] != '0') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Question205 question205 = new Question205();
        String s = "aba";
        String t = "baa";
        System.out.println(question205.isIsomorphic(s, t));
    }
}
