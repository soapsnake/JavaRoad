package com.soapsnake.algorithms.leetcode.math;

import java.util.stream.Stream;

public class Question537 {

    //leetcode537
    public String complexNumberMultiply(String a, String b) {
        int[] coefs1 = Stream.of(a.split("\\+|i")).mapToInt(Integer::parseInt).toArray(),
                coefs2 = Stream.of(b.split("\\+|i")).mapToInt(Integer::parseInt).toArray();
        return (coefs1[0]*coefs2[0] - coefs1[1]*coefs2[1]) + "+" + (coefs1[0]*coefs2[1] + coefs1[1]*coefs2[0]) + "i";
    }

    public String complexNumberMultiply2(String a, String b) {

        String result = "";
        String[] A = a.split("\\+");
        String[] B = b.split("\\+");
        int a1 = Integer.parseInt(A[0]);
        int b1 = Integer.parseInt(A[1].replace("i", ""));

        int a2 = Integer.parseInt(B[0]);
        int b2 = Integer.parseInt(B[1].replace("i", ""));

        int a1a2 = a1 * a2;
        int b1b2 = b1 * b2;
        int a1b2a2b1 = (a1 * b2) + (b1 * a2);

        String afinal = (a1a2 + (-1 * b1b2)) + "";
        String bfinal = a1b2a2b1 + "i";
        result = afinal + "+" + bfinal;
        return result;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

}
