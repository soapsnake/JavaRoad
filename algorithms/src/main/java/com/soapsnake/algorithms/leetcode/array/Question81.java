package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-06 23:08
 */
public class Question81 {

    /**
     * 在一个本来是升序的但是被重排的数组里,找出target是否存在
     */
    public boolean search(int[] nums, int target) {
        // note here end is initialized to len instead of (len-1)
        int start = 0, end = nums.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > nums[start]) { // nums[start..mid] is sorted
                // check if target in left half
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] < nums[start]) { // nums[mid..end] is sorted
                // check if target in right half
                if (target > nums[mid] && target < nums[start]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            } else { // have no idea about the array, but we can exclude nums[start] because nums[start] == nums[mid]
                start++;
            }
        }
        return false;
    }

    private boolean binerSearch(int[] nums, int left, int right, int target) {
        if (left > right) {
            return false;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return true;
        } else if (nums[mid] > target) {
            return binerSearch(nums, left, mid - 1, target);
        } else {
            return binerSearch(nums, mid + 1, right, target);
        }
    }


    public int search2(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                //到这里,只是能够肯定0 -> mid一定是有序递增的
                // 左半边有序
                if (nums[0] <= target && target <= nums[mid]) {
                    // target的值在左半边
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                //到这里,只是能够肯定mid -> len - 1一定是有序递增的
                // 右半边有序
                if (nums[mid] <= target && target <= nums[nums.length - 1]) {
                    // target的值在右半边
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}