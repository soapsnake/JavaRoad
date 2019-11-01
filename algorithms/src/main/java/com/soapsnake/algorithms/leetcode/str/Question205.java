package com.soapsnake.algorithms.leetcode.str;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-03 20:56
 */
public class Question205 {

    public static void main(String[] args) {
        Question205 question205 = new Question205();
        String s = "abb";
        String t = "egg";
        System.out.println(question205.isIsomorphic2(s, t));
    }

    //还是没有看懂
    public boolean isIsomorphic(String s, String t) {
        if (s == null || s.length() <= 1) return true;
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (map.containsKey(a)) {
                if (map.get(a).equals(b))
                    continue;
                else
                    return false;
            } else {
                if (!map.containsValue(b))
                    map.put(a, b);
                else return false;

            }
        }
        return true;
    }

    //没有搞懂
    public boolean isIsomorphic2(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            System.out.println("m1[s.charAt(" + i + ")] = " + m1[s.charAt(i)]);
            System.out.println("m2[t.charAt(" + i + ")] = " + m2[t.charAt(i)]);
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) {   //char字符会转换成ASCii值
                return false;
            }
            m1[s.charAt(i)] = i + 1;  //a -> 1   b -> 1
            m2[t.charAt(i)] = i + 1; //b -> 1    a -> 1
        }
        return true;
    }

    //思路:同时遍历s和t,比对同索引字符,如果不一致就改掉s中的字符,同时维护一个数子数组,对应位修改的话就是1,表示不能修改了,为0表示可以改
    public boolean isIsomorphic3(String t, String s) {
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            if (m1.put(t.charAt(i), i) != m2.put(s.charAt(i), i)) {
                return false;
            }
        }
        return true;
    }
}
