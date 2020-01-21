package com.soapsnake.algorithms.leetcode.array;

import java.util.List;

public class ArrayUtils {

    public static void printArr(int[] arr) {
        if (arr == null) {
            System.out.println("数组为空");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
    }

    public static <T> void printList(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("list 为空");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i == (list.size() - 1)) {
                System.out.println("\n");
            } else {
                System.out.print(" -> ");
            }
        }
    }

    public static void swapByIndex(int[] nums, int index, int i) {
        int temp = nums[index];
        nums[index] = nums[i];
        nums[i] = temp;
    }
}
