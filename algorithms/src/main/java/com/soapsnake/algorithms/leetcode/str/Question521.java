package com.soapsnake.algorithms.leetcode.str;

/**
 * @author soapsnake
 * @date 2018/11/12
 */
class Question521 {

    public int findLUSlength(String a, String b) {

        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }

    public static void main(String[] args) {
        Question521 question521 = new Question521();
        System.out.println(question521.findLUSlength("aba", "cdc"));

    }

}
