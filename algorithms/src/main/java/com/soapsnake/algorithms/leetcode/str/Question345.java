package com.soapsnake.algorithms.leetcode.str;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-16 21:58
 */
public class Question345 {

    /**
     * 一遍过就是这么牛逼!!!!!!!!
     */
    private static List<Character> vows = Arrays.asList('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U');

    public String reverseVowels(String s) {

        //左右指针碰撞算法
        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (right > left) {

            while (right > left) {
                if (this.isvow(chars[left])) {
                    break;
                }
                left++;
            }

            while (right > left) {
                if (this.isvow(chars[right])) {
                    break;
                }
                right--;
            }

            if (right > left) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
            }
            right--;
            left++;
        }
        return new String(chars);
    }

    private boolean isvow(char aChar) {
        return vows.contains(aChar);
    }
}
