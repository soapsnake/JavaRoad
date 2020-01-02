package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

public class Question324 {

    public static void main(String[] args) {
        Question324 question324 = new Question324();
        int[] nums = {3, 2, 5, 1, 8, 6};
        int left = 0;
        int right = 3;
        System.out.println(question324.findKthLargest(nums, 3));
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 题目要求o(n)复杂度,不使用额外内存
     * Example 1:
     * Input: nums = [1, 5, 1, 1, 6, 4]
     * Output: One possible answer is [1, 4, 1, 5, 1, 6].
     *
     *
     *
     * Example 2:
     * Input: nums = [1, 3, 2, 2, 3, 1]
     * Output: One possible answer is [2, 3, 1, 3, 1, 2].
     *
     * 解法解释:https://leetcode.com/problems/wiggle-sort-ii/discuss/77682/Step-by-step-explanation-of-index-mapping-in-Java
     */
    //3ms version
    public void wiggleSort2(int[] nums) {
        int n = nums.length;
        int mid = quickSearch(nums, 0, n - 1, n >> 1);
        int even = 1;
        int odd = (n & 1) == 0 ? n - 2 : n - 1;    //odd索引从后往前取的
        int idx = 0;
        while (idx < n) {
            if (nums[idx] > mid && (idx > even || (idx & 1) == 0)) {
                swap(nums, idx, even);
                even += 2;
            } else if (nums[idx] < mid && ((idx & 1) == 1 || odd > idx)) {
                swap(nums, idx, odd);
                odd -= 2;
            } else {
                idx++;
            }
        }
    }

    //这个找数组中位数的函数是整个算法的关键
    public int quickSearch(int[] nums, int start, int end, int k) {
        int pivot = (nums[start] + nums[end]) >> 1;  //中间值
        int left = start;
        int right = end;

        //类似快排
        while (left <= right) {
            while (nums[left] < pivot) left++;
            //到这里时nums[left]超过了中轴值,需要进行交换
            while (nums[right] > pivot) right--;
            //到这里时nums[right]小于了中轴值,需要进行交换
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }

        }
        //这个k的值始终都是指向数组的中间的,从来没变过
        if (start <= k && k <= right) {
            return quickSearch(nums, start, right, k);  //左半区间
        } else if (left <= k && k <= end) {
            return quickSearch(nums, left, end, k);   //右半区间
        }
        return nums[k];
    }


    private void swap(int[] nums, int leftIndex, int rightIndex) {
        int temp = nums[leftIndex];
        nums[leftIndex] = nums[rightIndex];
        nums[rightIndex] = temp;
    }

    public void wiggleSort(int[] nums) {
        if (nums == null) {
            return;
        }
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;

        int left = 0, i = 0, right = n - 1;
        while (i <= right) {
            if (nums[newIndex(i,n)] > median) {
                swap(nums, newIndex(left++, n), newIndex(i++, n));
            } else if (nums[newIndex(i, n)] < median) {
                swap(nums, newIndex(right--, n), newIndex(i, n));
            } else {
                i++;
            }
        }
    }

    //找数组中第k大的数字,如果i是nums的中位索引,那么该函数处理过后,nums将会形成i以前的数字都比i小,i以后的数字都比i大
    private int findKthLargest2(int[] nums, int i) {
        int left = 0;
        int right = nums.length - 1;
        while (right > left) {
            System.out.println("right = " + right + " left= " + left);
            while (nums[left] <= nums[i]) {
                left++;
            }
            while (nums[right] >= nums[i]) {
                right--;
            }
            if (right >= left) {
                swap(nums, right, left);
                left++;
                right--;
            }
        }
        return nums[i];
    }



    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }

    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k; // convert to index of k largest
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            //nums[left]的值作为中轴,[left, i] < nums[left]  [i + 1, j] >= nums[left]
            int i = left; // partition [l,r] by A[l]: [l,i]<A[l], [i+1,j)>=A[l]
            for (int j = left + 1; j <= right; j++) {
                if (nums[j] < nums[left]) {
                    swap(nums, j, ++i);
                }
            }
            swap(nums, left, i);
            if (k < i) right = i - 1;
            else if (k > i) left = i + 1;
            else return nums[i];
        }
        return -1; // k is invalid
    }
}
