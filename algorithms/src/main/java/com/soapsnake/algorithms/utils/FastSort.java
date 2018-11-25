package com.soapsnake.algorithms.utils;

public class FastSort {

    //https://zh.wikipedia.org/zh-cn/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F
    public static void fastSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = arr[(head + tail) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                ++i;
            }
            while (arr[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        fastSort(arr, head, j);
        fastSort(arr, i, tail);
    }


    //本人写的版本
    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || low >= high || arr.length <= 1) {
            return;
        }

        int left = low;
        int right = high;
        int middle = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left] < middle) {
                ++left;
            }
            while (arr[right] > middle) {
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
        }

        quickSort(arr, low, right);
        quickSort(arr, left, high);

    }


}
