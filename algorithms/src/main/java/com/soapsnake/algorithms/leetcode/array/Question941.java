package com.soapsnake.algorithms.leetcode.array;

import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/7 01:02
 */
public class Question941 {

    public static void main(String[] args) {
        Question941 question941 = new Question941();
        int[] a = {0, 3, 2, 1};
        System.out.println(question941.validMountainArray(a));
    }

    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) return false;
        int start = 0;
        int end = arr.length-1;
        while (start < end) {
            if (arr[start+1] > arr[start]) {
                start++;
            } else if (arr[end-1] > arr[end]) {
                end--;
            } else {
                break;
            }
        }
        return start != 0 && end != arr.length-1 && start == end;
    }

}
