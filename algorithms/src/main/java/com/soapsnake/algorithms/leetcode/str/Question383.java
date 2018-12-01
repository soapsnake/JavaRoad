package com.soapsnake.algorithms.leetcode.str;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/11/30 21:14
 */
public class Question383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        //a中的字符是否都在b里面
        char[] a = ransomNote.toCharArray();
        char[] b = magazine.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : b) {
            Integer temp = map.get(c);
            map.put(c, null == temp ? 1 : temp + 1);
        }

        for (char c : a) {
            Integer temp2 = map.get(c);
            if (temp2 == null)
                return false;   //a里有b没有的字符(例如: abc -> aab)
            if (temp2 - 1 < 0) {
                return false;   //a里的某字符出现次数比b中的多(例如:aaa -> aab)
            }
            map.put(c, temp2 - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        Question383 question383 = new Question383();
        String a = "aa";
        String b = "aab";
        System.out.println(question383.canConstruct(a, b));
    }
}
