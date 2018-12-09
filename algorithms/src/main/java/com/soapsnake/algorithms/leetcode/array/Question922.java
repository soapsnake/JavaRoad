package com.soapsnake.algorithms.leetcode.array;

class Question922 {

    public static void main(String[] args) {
        Question922 question922 = new Question922();
        int[] a = new int[]{4, 1, 1, 0, 1, 0};
        int[] res = question922.sortArrayByParityII(a);
        ArrayUtils.printArr(res);
    }

    //有问题:左指针必须和右指针的情形必须反过来
    public int[] sortArrayByParityII(int[] A) {
        if (A.length % 2 != 0) {  //元素必然成对
            return null;
        }

        for (int left = 0, right = A.length - 1; right > left; ) {
            if (left % 2 == 0 & A[left] % 2 != 0) {
                while (right > left) {
                    if (right % 2 != 0 & A[right] % 2 == 0) {
                        int temp = A[left];
                        A[left] = A[right];
                        A[right] = temp;
                        System.out.println("right = " + right);
                        break;
                    }
                    right--;
                }
            }
            if (left % 2 != 0 & A[left] % 2 == 0) {
                while (right > left) {
                    if (right % 2 == 0 & A[right] % 2 != 0) {
                        int temp = A[left];
                        A[left] = A[right];
                        A[right] = temp;
                        System.out.println("2right = " + right);
                        break;
                    }
                    right--;
                }
            }
            left++;
        }
        return A;
    }

    //todo 解法:奇数指针和偶数指针
    public int[] sortArrayByParityII2(int[] A) {
        return null;
    }
}