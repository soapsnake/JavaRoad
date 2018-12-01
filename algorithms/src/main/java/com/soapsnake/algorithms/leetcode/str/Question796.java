package com.soapsnake.algorithms.leetcode.str;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/1 16:00
 */
public class Question796 {

    public boolean rotateString(String A, String B) {
        char[] charsA = A.toCharArray();
        char[] charsB = B.toCharArray();

        if (charsA.length != charsB.length) {
            return false;
        }
        if (charsA.length == 0) {
            return false;
        }
        List<Character> res = new LinkedList<>();
        for (int i = 0; i < charsA.length; i++) {
            res.add(charsA[i]);
        }
        int i = 0;
        while (i <= charsA.length) {
            Character c = ((LinkedList<Character>) res).pollFirst();
            ((LinkedList<Character>) res).addLast(c);
            System.out.println(Arrays.toString(res.toArray()));
            int k = 0;
            Character[] temp = res.toArray(new Character[0]);
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] != charsB[j]) {
                    break;
                }
                k++;
            }
            if (k == temp.length) {
                return true;
            }
            i++;
        }
        return false;
    }

    //todo 能不能用string.contains来解这个问题???

    public static void main(String[] args) {
        Question796 question796 = new Question796();
        System.out.println(question796.rotateString("aa", "a"));
    }
}
