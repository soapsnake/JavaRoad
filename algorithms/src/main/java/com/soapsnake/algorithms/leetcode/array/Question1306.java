package com.soapsnake.algorithms.leetcode.array;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-11-29
 */
public class Question1306 {

    //leetcode1306
    public boolean canReach(int[] arr, int start) {
        if (start >= 0 && start < arr.length && arr[start] < arr.length) {
            int jump = arr[start];
            arr[start] += arr.length;
            return jump == 0 || canReach(arr, start + jump) || canReach(arr, start - jump);
        }
        return false;
    }
}
