package com.soapsnake.algorithms.leetcode.array;

class Question922 {

    public static void main(String[] args) {
        Question922 question922 = new Question922();
        int[] a = new int[]{3,2};
        int[] res = question922.sortArrayByParityII(a);
        ArrayUtils.printArr(res);
    }

    public int[] sortArrayByParityII(int[] A) {
        int even = 0;
        int odd = 1;
        int length = A.length;

        while (even < length && odd < length) {
            while (even < length && A[even] % 2 == 0) {
                even += 2;
            }

            while (odd < length && A[odd] % 2 != 0) {
                odd += 2;
            }

            if (even < length && odd < length) {
                int temp = A[even];
                A[even] = A[odd];
                A[odd] = temp;
            }
        }
        return A;
    }
}