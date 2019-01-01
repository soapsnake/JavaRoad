package com.soapsnake.algorithms.leetcode.str;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/4 19:15
 */
public class Question387 {

    public int firstUniqChar(String s) {

        //todo freqen[]

        int[] frequen = new int[26];
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            frequen[chars[i] - 'a']++;
        }
        System.out.println(Arrays.toString(frequen));
        for (int i = 0; i < chars.length; i++) {
            if (frequen[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Question387 question387 = new Question387();
        String tar = "loveleetcode";

        String tar1 = "cc";

        System.out.println(question387.firstUniqChar(tar));
    }
}
