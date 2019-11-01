package com.soapsnake.algorithms.leetcode.number;

public class Question263 {

    public static void main(String[] args) {
        Question263 question263 = new Question263();
        System.out.println(question263.isUgly(14));
    }

    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        if (num % 2 == 0) {
            return isUgly(num / 2);
        } else if (num % 3 == 0) {
            return isUgly(num / 3);
        } else if (num % 5 == 0) {
            return isUgly(num / 5);
        } else {
            return false;
        }
    }
}
