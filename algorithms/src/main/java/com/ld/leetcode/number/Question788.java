package com.ld.leetcode.number;

/**
 * @author soapsnake
 * @date 2018/11/21
 */
public class Question788 {

    public int rotatedDigits(int N) {
        int good = 0;
        for (int i = 1; i <= N; i++) {
            if (isValid(i))
                good++;
        }
        return good;
    }

    private boolean isValid(int n) {
        boolean isvalid = false;
        while (n > 0) {
            if (n % 10 == 2) {
                isvalid = true;
            }
            if (n % 10 == 5) {
                isvalid = true;
            }
            if (n % 10 == 6) {
                isvalid = true;
            }
            if (n % 10 == 9) {
                isvalid = true;
            }
            if (n % 10  == 3) {
                return false;
            }
            if (n % 10  == 4) {
                return false;
            }
            if (n % 10  == 7) {
                return false;
            }
            n = n / 10;
        }
        return isvalid;
    }

    //todo 利用动态规划解决这个问题

    public static void main(String[] args) {
        Question788 question788 = new Question788();
        System.out.println(question788.rotatedDigits(857)); //247
    }
}
