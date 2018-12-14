package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-14 00:41
 */
public class Question58 {

    public int lengthOfLastWord(String s) {
        if (s == null) {
            return 0;
        }
        if (s.equals("")) {
            return 0;
        }

        String[] strings = s.split(" ");
        if (strings.length == 0) {
            return 0;
        }
        if (strings[strings.length - 1].equals("")) {
            return 0;
        }
        return strings[strings.length - 1].length();
    }

    public static void main(String[] args) {
        String string = " ";
        Question58 question58 = new Question58();
        System.out.println(question58.lengthOfLastWord2(string));
    }

    public int lengthOfLastWord2(String s) {
        int i = s.length();
        if (i ==0) {
            return 0;
        }
        if (i == 1 && s != " ") {
            return 1;
        }
        for (int j = i - 1; j >= 0; j--) {
            if (s.charAt(j) == ' ') {
                return i - j - 1;
            }
        }
        return 0;
    }

}
