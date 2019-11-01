package com.soapsnake.algorithms.leetcode.str;

import java.util.Arrays;
import java.util.List;

/**
 * @author soapsnake
 * @date 2018/11/15
 */
class Question824 {

    public static void main(String[] args) {
        Question824 question824 = new Question824();
        System.out.println(question824.toGoatLatin("I speak Goat Latin"));
    }

    //纯碎就是繁琐
    public String toGoatLatin(String S) {
        String[] strings = S.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (isVowel(strings[i])) {
                strings[i] += "ma";
            } else {
                char head = strings[i].charAt(0);
                String newString = strings[i].substring(1);
                newString += (head + "ma");
                strings[i] = newString;
            }

            for (int j = 0; j <= i; j++) {
                strings[i] += "a";
            }
        }
        return String.join(",", strings);
    }

    private boolean isVowel(String string) {
        List<Character> list = Arrays.asList('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U');
        return list.contains(string.charAt(0));
    }
}
