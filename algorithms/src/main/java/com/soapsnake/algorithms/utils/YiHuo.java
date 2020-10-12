package com.soapsnake.algorithms.utils;

import java.util.Arrays;

/**
 * Created by soapsnake on 2018/1/29.
 */
public class YiHuo {

    public static void main(String[] args) {
        int i1 = 22;
        int i2 = 24;

        int i3 = i1 ^ i2;

        int i4 = i1 ^ 0;

        int i5 = i2 ^ 0;

        System.out.println(i3);
        System.out.println(i4);
        System.out.println(i5);

        int[] ints = {7, 4, 8, 9, 3, 4, 1, 0};

        quickSort(ints, 0, ints.length - 1);
        System.out.println(Arrays.toString(ints));

    }

    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || low >= high || arr.length <= 1) {
            return;
        }

        int left = low;
        int right = high;
        int midd = arr[(left + right) / 2];

        while (left <= right) {
            while (arr[left] < midd) {
                ++left;
            }

            while (arr[right] > midd) {
                --right;
            }

            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                ++left;
                --right;
            } else if (left == right) {
                ++left;
            }

            //left > right 绝对不相等!!!!
            quickSort(arr, low, right);
            quickSort(arr, left, high);
        }
    }
}
