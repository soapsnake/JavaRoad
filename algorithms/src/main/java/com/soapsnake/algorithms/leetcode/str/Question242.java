package com.soapsnake.algorithms.leetcode.str;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/11/26 20:37
 */
public class Question242 {

    public static void main(String[] args) {
        Question242 question242 = new Question242();
        System.out.println(question242.isAnagram("anagram", "nagaram"));
    }

    //leetcode242
    public boolean isAnagram(String s, String t) {

        //O(nlogn)
        if (s.length() != t.length()) {
            return false;
        }

        char[] charsS = s.toCharArray();
        int[] intS = new int[s.length()];
        int[] intT = new int[s.length()];
        for (int i = 0; i < charsS.length; i++) {
            intS[i] = charsS[i] - 'a';
        }

        char[] charsT = t.toCharArray();
        for (int i = 0; i < charsT.length; i++) {
            intT[i] = charsT[i] - 'a';
        }
        Arrays.sort(intS);
        Arrays.sort(intT);
        return Arrays.equals(intS, intT);
    }

    //todo O(n)解法, 提示:额外数组
    public boolean isAnagram2(String s, String t) {
        return false;
    }
}
