package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/7 01:02
 */
public class Question941 {

    public boolean validMountainArray(int[] A) {
        if (A.length == 0 || A.length == 1) {
            return false;
        }

        int maxAbsence = 0;
        if (A[1] < A[0]) {
            return false;
        }

        for (int i = 1; i < A.length - 1; i++) {
            if (A[i]  > A[i + 1] && maxAbsence == 0) {
                maxAbsence = 1;
                continue;
            }

            if (maxAbsence == 1 && A[i + 1] >= A[i]) {
                return false;
            }
        }
        return maxAbsence == 1;
    }

    public static void main(String[] args) {
        Question941 question941 = new Question941();
        int[] a = {0, 3, 2, 1};
        System.out.println(question941.validMountainArray(a));
    }
}
