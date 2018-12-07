package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/7 00:21
 */
public class Question949 {

    public String largestTimeFromDigits(int[] A) {
        if (A.length != 4) {
            return "";
        }

        //大于5的数字只能有1个,大于3的数字只能有2个,

        int big5 = 0;
        int big3 = 0;

        Arrays.sort(A);
        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 0; i < A.length; i++) {
            if (A[i] > 5) {
                big5++;
            }
            if (A[i] > 3) {
                big3++;
            }
            if (big5 > 1) {
                return "";
            }
            if (big3 > 2) {
                return "";
            }
        }

        if (A[0] != 3) {
            stringBuilder.append(A[0]);
            stringBuilder.append(A[1]);
        } else {
            stringBuilder.append(A[1]);
            stringBuilder.append(A[0]);
        }

        stringBuilder.append(":");
        if (big5 == 1) {
            stringBuilder.append(A[3]);
            stringBuilder.append(A[2]);
        } else {
            stringBuilder.append(A[2]);
            stringBuilder.append(A[3]);
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) {
        Question949 question949 = new Question949();
        int[] a = {1,2,3,4};
        System.out.println(question949.largestTimeFromDigits(a));
    }
}
