package com.ld.leetcode.str;

import java.util.HashMap;
import java.util.Map;

/**
 * @author soapsnake
 * @date 2018/11/19
 */
public class Question389 {

    public char findTheDifference(String s, String t) {
        char[] charOfs = s.toCharArray();
        Map<Character, Integer> mapS = new HashMap<>();
        for (char c1 : charOfs) {
            if (mapS.containsKey(c1)) {
                mapS.put(c1, mapS.get(c1)+1);
            } else {
                mapS.put(c1, 1);
            }
        }
        char[] charOft = t.toCharArray();
        char res = ' ';

        for (char c2 : charOft) {
            if (!mapS.containsKey(c2)) {
                return c2;
            } else {
                mapS.replace(c2, mapS.get(c2) - 1);
                if (mapS.get(c2) == 0) {
                    mapS.remove(c2);
                }
            }
        }
        return res;
    }

    //todo 利用ASCII码的O(n)解法,不需要额外内存

    public static void main(String[] args) {
       String s = "a";
       String t = "aa";

       Question389 question389 = new Question389();
        System.out.println(question389.findTheDifference(s, t));
    }
}
