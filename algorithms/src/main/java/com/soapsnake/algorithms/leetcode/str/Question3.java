package com.soapsnake.algorithms.leetcode.str;

import java.util.HashMap;
import java.util.Map;

class Question3 {

    public static void main(String[] args) {
        Question3 question3 = new Question3();
        String str = "abcabcbb";
        String str2 = "pwwkew";
        System.out.println(question3.lengthOfLongestSubstring(str));
    }

    public int lengthOfLongestSubstring3(String s) {
        if (s == null) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, (i - j) + 1);
        }
        return max;
    }


    //最傻逼解法,时间复杂度平方级别
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.equals("")) {
            return 0;
        }
        if (s.equals(" ")) {
            return 1;
        }
        char[] chars = s.toCharArray();
        int max = -Integer.MAX_VALUE;
        String temp = "";
        for (int i = 0; i < chars.length; i++) {
            temp += chars[i];
            if (chars.length - i <= max) {
                return max;
            }
            for (int j = i + 1; j < chars.length; j++) {
                if (temp.contains(chars[j] + "")) {
                    break;
                } else {
                    temp += chars[j];
                }
            }
            max = Math.max(temp.length(), max);
            temp = "";
        }
        return max;
    }


    //    the basic idea is, keep a hashmap which stores the characters in string as keys
//    and their positions as values, and keep two pointers which define the max substring.
//    move the right pointer to scan through the string , and meanwhile update the hashmap.
//    If the character is already in the hashmap, then move the left pointer to the right of
//    the same character last found. Note that the two pointers can only move forward.
    public int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            //如果map中已经存在i处的字符
            if (map.containsKey(s.charAt(i))) {
                //j只会取到i处字符的最大索引的下一个索引值
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);   //注意了,如果i处字符之前出现过,字符的value会变更
            max = Math.max(max, i - j + 1);
        }
        return max;
    }
}
