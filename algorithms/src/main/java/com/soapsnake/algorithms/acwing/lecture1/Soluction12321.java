package com.soapsnake.algorithms.acwing.lecture1;

import java.util.Arrays;

public class Soluction12321 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i = n, j = 0; i < m && j < n; i++, j++) {
            nums1[i] = nums2[j];
//            System.out.println("i = " + i + " j = " + j + " " + Arrays.toString(nums1));
        }
        Arrays.sort(nums1);
    }

    public static void main(String[] args) {
        Soluction12321 s = new Soluction12321();
        int[] i1 = {1,2,3,0,0,0};
        int[] i2 = {2,5,6};
        s.merge(i1, 6, i2, 3);
        System.out.println(Arrays.toString(i1));
    }
}
