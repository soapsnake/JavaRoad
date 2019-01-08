package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.Random;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-08 14:01
 */
public class Sorts {

    /**
     * 插入排序
     * 每一次取数组剩余数字中的最小数,与数组头交换
     */
    public static void insertSorts(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int smallest = Integer.MAX_VALUE;
            int smallIndex = -1;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] <= smallest) {
                    smallest = nums[j];
                    smallIndex = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[smallIndex];
            nums[smallIndex] = temp;
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 冒泡排序
     * 两两比较
     */
    public static void bubleSorts(int[] nums) {


    }

    /**
     *合并排序
     */
    public static void mergeSorts(int[] nums) {


    }

    /**
     *希尔排序
     */
    public static void hillSorts(int[] nums) {


    }

    /**
     *快排
     */
    public static void quicktSorts(int[] nums) {


    }

    /**
     *堆排
     */
    public static void heapSorts(int[] nums) {


    }

    public static int[] randomArr(int n) {
        Random random = new Random();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = random.nextInt(100);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = randomArr(10);
        System.out.println("nums1 = " + Arrays.toString(nums1));
        insertSorts(nums1);

        int[] nums2 = randomArr(10);
        System.out.println("nums2 = " + Arrays.toString(nums2));
        bubleSorts(nums2);

        int[] nums3 = randomArr(10);
        System.out.println("nums3 = " + Arrays.toString(nums3));
        mergeSorts(nums3);

        int[] nums4 = randomArr(10);
        System.out.println("nums4 = " + Arrays.toString(nums4));
        hillSorts(nums4);

        int[] nums5 = randomArr(10);
        System.out.println("nums5 = " + Arrays.toString(nums5));
        quicktSorts(nums5);

        int[] nums6 = randomArr(10);
        System.out.println("nums6 = " + Arrays.toString(nums6));
        heapSorts(nums6);

        int[] nums7 = randomArr(10);
        System.out.println("nums7 = " + Arrays.toString(nums7));
    }

}
