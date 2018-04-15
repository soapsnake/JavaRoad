package com.ld.leetcode;

public class Question198 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2){
            return Math.max(nums[0], nums[1]);
        }
        if (nums.length == 3){
            return Math.max(nums[0] + nums[2], nums[1]);
        }

        this.quickSort(nums, 0, nums.length - 1);
        int max = 0;
        for (int i = nums.length - 1; i >= 0; i -= 2) {
            max += nums[i];
        }
        return max;
    }

    void quickSort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            quickSort(arr, left, index - 1);
        }
        if (index < right) {
            quickSort(arr, index, right);
        }
    }

    int partition(int arr[], int left, int right) {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];
        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }


    public static void main(String[] args) {
        Question198 question198 = new Question198();
        int[] robs = new int[]{1, 2, 1, 0};
        System.out.println(question198.rob(robs));
    }
}
