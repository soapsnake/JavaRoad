package com.ld.leetcode.array;

/**
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 * You need to find the shortest such subarray and output its length.
 * 老实说这个题目我压根就没有看懂
 */
class Question581 {


    public static void main(String[] args) {
        Question581 question581 = new Question581();
        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        System.out.println(question581.findUnsortedSubarray2(nums));
    }

    /**
     * 这道题目的最基本的思路:
     * 我先把这个数组进行排序,然后挨个比较原始数组和排序好的数组,找到的左侧第一个不相同的元素就是子数组的左边界
     * 找到的右侧的最后一个不相同的元素就是子数组的右边界
     */
    public int findUnsortedSubarray(int[] nums) {
        //TODO 这个解法没有搞懂
        int n = nums.length, beg = -1, end = -2, min = nums[n - 1], max = nums[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[n - 1 - i]);
            if (nums[i] < max) end = i;
            if (nums[n - 1 - i] > min) beg = n - 1 - i;
        }
        return end - beg + 1;
    }

    /**
     * TODO 这个解法还是没有能够搞明白
     * 双路解法:
     * 1. nums[0, i-1] 和 nums[j+1, n-1]都是排好序的
     * 2. nums[i] != nums_sorted[i] and nums[j] != nums_sorted[j].
     * 3. nums[i - 1] <= min and max <= nums[j + 1], where min and max are the minimum and maximum values of subarray nums[i, j].
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray2(int[] nums) {
        int l = 0, r = nums.length - 1, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        while (l < r && nums[l] <= nums[l + 1]) l++;   //退出这个循环时,l很可能是子数组的左边界
        if (l >= r) return 0;   //如果l和r相等,那说明这整个数组都是排好序的
        while (nums[r] >= nums[r - 1]) r--;   //退出这个循环时,r很可能是子数组的右边界
        for (int k = l; k <= r; k++) {      //对可能的子数组进行遍历,找出可能子数组的最大值和最小值
            max = Math.max(max, nums[k]);
            min = Math.min(min, nums[k]);
        }

        while (l >= 0 && min < nums[l]) l--;
        while (r < nums.length && nums[r] < max) r++;
        return (r - l - 1);
    }
}
