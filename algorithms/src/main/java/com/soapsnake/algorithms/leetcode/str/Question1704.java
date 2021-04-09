package com.soapsnake.algorithms.leetcode.str;

import java.util.*;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-04-08
 */
public class Question1704 {

    //leetcode1704
    public boolean halvesAreAlike(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int a = 0, b = 0;

        //左右指针
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            a += vowels.contains(s.charAt(i)) ? 1 : 0;
            b += vowels.contains(s.charAt(j)) ? 1 : 0;
        }
        return a == b;
    }
}
