package com.soapsnake.algorithms.leetcode.array;

/**
 * @author soapsnake
 * @date 2018/11/15
 */
class Question896 {

    public static void main(String[] args) {
        Question896 question896 = new Question896();
        int[] A = {1, 1, 2};
        System.out.println(question896.isMonotonic(A));
    }

    public boolean isMonotonic(int[] A) {

        int crease = 0;   // 1 zeng 2jian

        for (int i = 0; i < A.length - 1; i++) {
            if (crease == 0) {
                if (A[i + 1] - A[i] == 0) {
                    continue;
                } else {
                    crease = A[i + 1] - A[i] > 0 ? 1 : 2;
                    continue;
                }
            }

            if (A[i + 1] - A[i] > 0 && crease == 2) {
                return false;
            }
            if (A[i + 1] - A[i] < 0 && crease == 1) {
                return false;
            }
        }
        return true;
    }
}
