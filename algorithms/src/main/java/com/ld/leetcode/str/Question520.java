package com.ld.leetcode.str;

/**
 * @author soapsnake
 * @date 2018/11/20
 */
 class Question520 {

    public boolean detectCapitalUse(String word) {
        char[] chars = word.toCharArray();
        if (chars.length == 1) {
            return true;
        }
        int higLeft = 'A';
        int higRight = 'Z';
        int first = 0;   //0:未初始化 1:大写 2:小写
        int accu = 0;
        if (chars[0] >= higLeft && chars[0] <= higRight) {
            first = 1;
            accu = 1;
        } else {
            first = 2;
        }
        for (int i = 1; i < chars.length; i++) {
            if (first == 1 && chars[i] >= higLeft && chars[i] <= higRight) {
                accu++;
            }
            if (first == 2 && chars[i] >= higLeft && chars[i] <= higRight) {
                return false;
            }
        }
        return first != 1 || accu <= 1 || accu >= chars.length;
    }

    public static void main(String[] args) {
        Question520 question520 = new Question520();
        System.out.println(question520.detectCapitalUse("ggjaijisd"));
    }


}
