package com.soapsnake.algorithms.utils;

import com.soapsnake.algorithms.structures.queue.MaxPriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-08 14:01
 */
public class Sorts {

    /**
     * 稳定排序
     * 选择排序O(n²)
     * 每一次取子数组i -> end中的最小数字与nums[i]交换
     */
    public static void selectionSort(int[] nums) {
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
        Long start = System.currentTimeMillis();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        System.out.println("bubleSorts结果:" +Arrays.toString(nums) + "用时:" + (System.currentTimeMillis() - start));
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
        Long start = System.currentTimeMillis();
        int low = 0;
        int high = nums.length - 1;
        quickSort(nums, low, high);
        System.out.println("quickSorts结果:" +Arrays.toString(nums) + "用时:" + (System.currentTimeMillis() - start));
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (arr == null || low >= high || arr.length <= 1) {
            return;
        }

        int left = low;
        int right = high;
        int middle = arr[(left + right) / 2];   //middle是中间值,不是中位索引
        while (left <= right) {
            while (arr[left] < middle) {
                ++left;
            }
            //到这里发现了左半数组比中值大的数字
            while (arr[right] > middle) {
                --right;
            }
            //到这里发现了右半数组比中值小的数字

            if (left < right) {   //如果左指针比右指针小,那么只交换数字
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                ++left;  //驱动
                --right;  //驱动
            } else if (left == right) {  //但是如果左右指针碰撞了??
                ++left;  //利用这个来退出外层循环,这里操作后left就绝对比right大了,太他吗精髓了这个
            }
        }
        //到这里,左半都比右半小,而且left > right

        quickSort(arr, low, right);  //左半继续,这个左右边界选的太他吗精髓了
        quickSort(arr, left, high);   //右半继续,这个左右边界选的太他吗精髓了
    }

    /**
     * 不稳定(最差O(n²))
     * 插入排序
     * i指针指向的数字与0 -> i - 1之间的数字进行比较,如果比j小,那么a[i]需要插到j位置处,j - i的元素依次往后移动一个位置
     */
    public static void insertSorts(int[] arr) {
        long start = System.currentTimeMillis();
        int n = arr.length;
        int i, j, k;
        for (i = 1; i < n; i++) {
            //在[0...i-1]区间内查找元素arr[i]待插入的位置
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    break;
                }
            }
            //如果找了待插入的位置
            if (j != i - 1) {
                int temp = arr[i];
                //比a[i]大的数据往后移动
                for (k = i - 1; k > j; k--) {
                    arr[k + 1] = arr[k];
                }
                //将a[i]放在待插入的位置
                arr[k + 1] = temp;
            }

        }
        System.out.println("insertSorts结果:" +Arrays.toString(arr) + "用时:" + (System.currentTimeMillis() - start));
    }

    /**
     *堆排,使用优先级队列
     */
    public static void heapSorts(int[] nums) {
        Long start = System.currentTimeMillis();
        MaxPriorityQueue<Integer> maxPriorityQueue = new MaxPriorityQueue<>(nums.length);
        for (int i : nums) {
            maxPriorityQueue.insert(i);
        }
        nums = Arrays.stream(maxPriorityQueue.sort()).mapToInt(Integer::intValue).toArray();
        System.out.println("heapSorts结果:" +Arrays.toString(nums) + "用时:" + (System.currentTimeMillis() - start));
    }

    /**
     * 桶排序
     * @param nums
     */
    public static void bucketSort(int[] nums) {
        Long start = System.currentTimeMillis();

        int max = nums[0], min = nums[0];
        for (int a : nums) {
            if (max < a)     //求数组的最大值和最小值
                max = a;
            if (min > a)
                min = a;
        }
        // 該值也可根據實際情況選擇
        int bucketNum = max / 10 - min / 10 + 1;     //取桶的数量是任意取得
        List<List<Integer>> buckList = new ArrayList<>();  //每一个桶里都是一个链表,和HashMap的结构一样
        // create bucket
        for (int i = 1; i <= bucketNum; i++) {
            buckList.add(new ArrayList<>());
        }
        // push into the bucket
        for (int i = 0; i < nums.length; i++) {

            //是不是很眼熟,非常像HashMap的索引计算
            int index = indexFor(nums[i], min, 10);   //这个step其实就是区间范围,比如1~10, 或者20~30
            buckList.get(index).add(nums[i]);
        }
        ArrayList<Integer> bucket = null;
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            bucket = (ArrayList<Integer>) buckList.get(i);
            insertSort(bucket);  //为何此处使用插入排序???
            for (int k : bucket) {
                nums[index++] = k;   //其实这个就是合并
            }
        }
        System.out.println("bucketSort结果:" +Arrays.toString(nums) + "用时:" + (System.currentTimeMillis() - start));
    }

    // 把桶內元素插入排序
    private static void insertSort(List<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int temp = bucket.get(i);
            int j = i - 1;
            for (; j >= 0 && bucket.get(j) > temp; j--) {
                bucket.set(j + 1, bucket.get(j));  //把arr[j]的值赋给arr[j+1]
            }
            bucket.set(j + 1, temp);
        }
    }

    private static int indexFor(int a, int min, int step) {
        return (a - min) / step;
    }



    public static void main(String[] args) {
        int[] nums1 = randomArr(10);
        System.out.println("nums1 = " + Arrays.toString(nums1));
        selectionSort(nums1);

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
        insertSorts(nums7);

        int[] nums8 = randomArr(10);
        System.out.println("nums8 = " + Arrays.toString(nums8));
        bucketSort(nums8);
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
