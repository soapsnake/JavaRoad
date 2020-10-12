package com.soapsnake.algorithms.leetcode.str;

/**
 * Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
 */
class Question709 {
    public String toLowerCase(String str) {

        char[] strings = str.toCharArray();
        String newstr = "";
        for (char s : strings) {
            if (s >= 'A' && s <= 'Z') {
                //s大写了，需要转小写
                newstr += (char) (s - ('A' - 'a'));
            } else {
                newstr += s;
            }
        }
        return newstr;
    }

}
