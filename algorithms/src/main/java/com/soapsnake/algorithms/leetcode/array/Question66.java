package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

public class Question66 {

    //leetcode66
    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return null;
        }
        int step = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int real = 0;
            if (digits[i] + step >= 10) {
                step = (digits[i] + 1) / 10;
                real = (digits[i] + 1) % 10;
                digits[i] = real;
                if (i == 0) {
                    int[] newarr = new int[digits.length + 1];
                    System.arraycopy(digits, 0, newarr, 1, digits.length);
                    newarr[0] = step;
                    digits = newarr;
                }
            } else {
                digits[i] = digits[i] + step;
                step = 0;
            }
        }
        return digits;
    }

    public static void main(String[] args) {
        Question66 question66 = new Question66();
        int[] digts = {9};
        System.out.println(Arrays.toString(question66.plusOne(digts)));
    }
}
