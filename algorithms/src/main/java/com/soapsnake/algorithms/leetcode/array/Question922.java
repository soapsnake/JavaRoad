package com.soapsnake.algorithms.leetcode.array;

class Question922 {

    public static void main(String[] args) {
        Question922 question922 = new Question922();
        int[] a = new int[]{5, 2, 4, 7};
        int[] res = question922.sortArrayByParityII(a);
        ArrayUtils.printArr(res);
    }

    //有问题:左指针必须和右指针的情形必须反过来
    public int[] sortArrayByParityII(int[] A) {
        if (A.length % 2 != 0) {  //元素必然成对
            return null;
        }

        int left = 0;
        int right = A.length - 1;
        while (right > left) {
            while (left < right) {
                if (left % 2 != 0 && A[left] % 2 == 0 || left % 2 == 0 && A[left] % 2 != 0) {
                    break;
                } else {
                    left++;
                }
            }

            while (right > left) {
                if (right % 2 == 0 && A[right] % 2 != 0) {
                    break;
                } else {
                    right--;
                }
            }

            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;
            left++;
            right--;
        }
        return A;
    }
}
