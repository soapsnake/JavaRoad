package com.ld.leetcode.array;

/**
 * @author soapsnake
 * @date 2018/11/4
 *
 * Given a positive integer N, find and return the longest distance between two consecutive 1's in the binary representation of N.
 * If there aren't two consecutive 1's, return 0.
 */

public class Question868 {
    //todo 题目例子没看明白?两个'1'之间的最大距离?
    public int binaryGap(int N) {
        String string = Integer.toBinaryString(N);
        int max = Integer.MIN_VALUE;
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('1' == chars[i]) {
                for (int j = chars.length - 1; j >= i; j--) {
                    if ('1' == chars[j]) {
                        max = Math.max(max, j - i);
                    }
                    if (j == i) {
                        break;
                    }
                }
            }
        }
        return max < 0 ? 0 : max;
    }


    public static void main(String[] args) {
        Question868 question868 = new Question868();
        System.out.println(question868.binaryGap(5));
    }
}
