package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-03 01:43
 */
public class Question350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> temp = new ArrayList<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++) {
            temp.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (temp.contains(nums2[i])) {
                res.add(nums2[i]);
            }
        }
        int[] resarr = new int[res.size()];
        int j = 0;
        for (Integer i : res) {
            resarr[j++] = i;
        }

        return  resarr;
    }

    public static void main(String[] args) {
        Question350 question350 = new Question350();
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        System.out.println(Arrays.toString(question350.intersect(nums1, nums2)));
    }
}
