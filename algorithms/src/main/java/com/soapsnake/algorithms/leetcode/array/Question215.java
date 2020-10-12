package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Question215 {

    public static void main(String[] args) {
        Question215 quesiont215 = new Question215();
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(quesiont215.findKthLargest3(nums, k)); //should be 4
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);  //升序
        return nums[nums.length - k];
    }

    public int findKthLargest2(int[] nums, int k) {
        final PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i : nums) {
            priorityQueue.offer(i);

            if (priorityQueue.size() > k) {  //pq中只保存前k大的数
                priorityQueue.poll();
            }
        }
        return priorityQueue.peek();  //堆顶元素就是第k大的数字,那么毫无疑问这个堆是一个小顶堆,堆顶元素最小
    }

    public int findKthLargest3(int[] nums, int k) {
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

    private void swap(int[] nums, int leftIndex, int rightIndex) {
        int temp = nums[leftIndex];
        nums[leftIndex] = nums[rightIndex];
        nums[rightIndex] = temp;
    }

}

