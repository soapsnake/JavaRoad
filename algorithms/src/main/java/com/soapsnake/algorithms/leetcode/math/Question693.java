package com.soapsnake.algorithms.leetcode.math;

/**
 * @author soapsnake
 * @date 2018/11/10
 */
class Question693 {

    public static void main(String[] args) {
        Question693 question693 = new Question693();
        System.out.println(question693.hasAlternatingBits(3));
    }

    public boolean hasAlternatingBits(int n) {
        String biner = Integer.toBinaryString(n);

        char[] arr = biner.toCharArray();
        int[] ints = new int[arr.length];
        int i = 0;
        for (char c : arr) {
            ints[i++] = Integer.valueOf(c + "");
        }

        for (int j = 0; j < ints.length - 1; j++) {
            if (ints[j + 1] - ints[j] == 0) {
                return false;
            }
        }
        return true;
    }
}
