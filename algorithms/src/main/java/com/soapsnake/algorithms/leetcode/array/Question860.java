package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/11/27 23:47
 */
public class Question860 {

    public static void main(String[] args) {
        Question860 question860 = new Question860();
        int[] bills = {5, 5, 5, 5, 20, 20, 5, 5, 20, 5};
        System.out.println(question860.lemonadeChange(bills));
    }

    public boolean lemonadeChange(int[] bills) {

        int countOf5 = 0;
        int countOf10 = 0;

        for (int i = 0; i < bills.length; i++) {
            if (i == 0) {
                if (bills[i] == 5) {
                    countOf5++;
                } else if (bills[i] == 10) {
                    countOf10++;
                }
                continue;
            }

            if (bills[i] == 5) {
                countOf5++;
                continue;
            }
            if (bills[i] == 10) {
                countOf10++;
                countOf5--;
                if (countOf5 < 0) {
                    return false;
                }
            }

            if (bills[i] == 20) {
                if (countOf10 >= 1) {
                    countOf10--;
                    countOf5--;
                    if (countOf5 < 0) {
                        return false;
                    }
                } else {
                    countOf5 -= 3;
                    if (countOf5 < 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
