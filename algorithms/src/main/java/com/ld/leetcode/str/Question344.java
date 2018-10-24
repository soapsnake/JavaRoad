package com.ld.leetcode.str;


/**
 * Write a function that takes a string as input and returns the string reversed.
 */
public class Question344 {

    public String reverseString(String s) {
        if (null == s || "".equals(s)){
            return s;
        }
        char[] chars = s.toCharArray();

        for (int i=0;i<chars.length / 2;i++){
            char temp = chars[i];
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = temp;
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        String s = "hello world";
        Question344 question344 = new Question344();
        System.out.println(question344.reverseString(s));

    }
}
