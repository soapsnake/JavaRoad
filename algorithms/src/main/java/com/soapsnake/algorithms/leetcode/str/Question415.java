package com.soapsnake.algorithms.leetcode.str;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/11 22:39
 */
public class Question415 {

    public String addStrings(String num1, String num2) {
        StringBuilder stringBuilder = new StringBuilder();
        String bigger = num1.length() > num2.length() ? num1 : num2;
        String smaller = num1.equals(bigger) ? num2 : num1;
        int diff = bigger.length() - smaller.length();
        String[] big = bigger.split("");
        String[] sma = smaller.split("");
        String[] sumstr = new String[big.length];
        for (int i = sumstr.length - 1; i >= 0; i--) {
            if (i - diff >= 0 ) {
                int temp = Integer.valueOf(big[i]) + Integer.valueOf(sma[i - diff]);
                sumstr[i] = temp + "";
            } else  {
                sumstr[i] = big[i];
            }
        }
        int step = 0;
        for (int i = sumstr.length - 1; i >=0; i--) {
            if (Integer.valueOf(sumstr[i]) + step >= 10) {
                int real = Integer.valueOf(sumstr[i]) + step - 10;
                stringBuilder.insert(0, real + "");
                step = 1;
            } else {
                stringBuilder.insert(0, Integer.valueOf(sumstr[i]) + step + "");
                step = 0;
            }
        }
        if (step == 1) {
            stringBuilder.insert(0, "1");
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Question415 question415 = new Question415();
        String num1 = "408";
        String num2 = "5";
        System.out.println(question415.addStrings(num1, num2));
    }
}
