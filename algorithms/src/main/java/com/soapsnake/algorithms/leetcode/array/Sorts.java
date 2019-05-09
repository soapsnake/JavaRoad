package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.Random;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-08 14:01
 */
public class Sorts {

    /**
     * 稳定排序
     * 插入排序O(n²)
     * 每一次取子数组i -> end中的最小数字与nums[i]交换
     */
    public static void insertSorts(int[] nums) {
        Long start = System.currentTimeMillis();
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
        System.out.println("insertSorts结果:" +Arrays.toString(nums) + "用时:" + (System.currentTimeMillis() - start));
    }

    /**
     * 冒泡排序
     * 两两比较
     */
    public static void bubleSorts(int[] nums) {


    }

    /**
     *归并排序
     *
     *
     * 思路:先利用递归对数组进行拆分,然后合并拆分的两个数组
     */
    public static void mergeSorts(int[] nums) {
        Long start = System.currentTimeMillis();
        mergeSortRecursive(nums, 0,nums.length - 1);
        System.out.println("mergeSorts结果:" +Arrays.toString(nums) + "用时:" + (System.currentTimeMillis() - start));
    }

    //归并排序的拆分过程
    private static void mergeSortRecursive(int[] arr, int start, int end) {
            //如果只有一个元素，那就不用排序了
            if (start == end) {
                return;
            } else {
                //取中间的数，进行拆分
                int mid = (start + end) / 2;

                //左边的数不断进行拆分
                mergeSortRecursive(arr, start, mid);

                //右边的数不断进行拆分
                mergeSortRecursive(arr, mid + 1, end);

                //合并
                merge(arr, start, mid + 1, end);
            }
    }

    //归并排序的合并过程
    private static void merge(int[] arrays, int left, int mid, int right) {
        //左半临时数组
        int[] leftTemp = new int[mid - left];

        //右半临时数组
        int[] rightTemp = new int[right - mid + 1];

        //往这两个数组填充数据,这个地方不进行大小的比较
        for (int i = left; i < mid; i++) {
            leftTemp[i - left] = arrays[i];
        }
        for (int i = mid; i <= right; i++) {
            rightTemp[i - mid] = arrays[i];
        }
        int i = 0, j = 0;
        // arrays数组的第一个元素
        int  k = left;  //left是初始值,第一轮就是0
        //比较这两个数组的值，哪个小，就往数组上放
        while (i < leftTemp.length && j < rightTemp.length) {
            //谁比较小，谁将元素放入大数组中,移动指针，继续比较下一个
            // 等于的情况是保证“稳定”
            if (leftTemp[i] <= rightTemp[j]) {
                arrays[k] = leftTemp[i];   //如果当前指针,左数组的元素比较小,那么递增的是左指针
                i++;
            } else {
                arrays[k] = rightTemp[j];
                j++;
            }
                k++;            //k指针每轮都会移动
        }

        //如果左边的数组还没比较完，右边的数都已经完了(右数组普遍比左数组的数字小)，那么将左边的数抄到大数组中(剩下的都是大数字,填补空位)
        while (i < leftTemp.length) {
            arrays[k] = leftTemp[i];
            i++;
            k++;
        }
        //如果右边的数组还没比较完，左边的数都已经完了(右数组普遍比左数组数字大)，那么将右边的数抄到大数组中(剩下的都是大数字,填补空位)
        while (j < rightTemp.length) {
            arrays[k] = rightTemp[j];
            k++;
            j++;
        }
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

    public static int[] randomArr(int n) {
        Random random = new Random();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = random.nextInt(100);
        }
        return res;
    }

}
