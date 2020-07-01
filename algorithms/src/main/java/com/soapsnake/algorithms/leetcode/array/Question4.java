package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-29
 */
public class Question4 {

    //hard难度
    //leetcode4
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int i = 0, j = 0, imin = 0, imax = m, half = (m + n + 1) / 2;
        double maxLeft = 0, minRight = 0;
        while (imin <= imax) {
            i = (imin + imax) / 2;
            j = half - i;
            if (j > 0 && i < m && nums2[j - 1] > nums1[i]) {
                imin = i + 1;
            } else if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {
                imax = i - 1;
            } else {
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                break;
            }
        }
        if ((m + n) % 2 == 1) {
            return maxLeft;
        }
        if (i == m) {
            minRight = nums2[j];
        } else if (j == n) {
            minRight = nums1[i];
        } else {
            minRight = Math.min(nums1[i], nums2[j]);
        }
        return (maxLeft + minRight) / 2;
    }

    public synchronized int  uniquePaths(int m, int n) {
        int[][] paths = new int[m][n];
        Arrays.fill(paths[0], 1);  //第0行全部填充1
        for (int i = 1; i < m; i++) {
            paths[i][0] = 1;   //第0列全部填充1
        }
        //上边和左边,每一个格子都只有一条路能到达,不存在其他路线
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
            }
        }
        return paths[m - 1][n - 1];
    }

    @Test
    public void testUNi() {
        int m = 7;
        int n = 3;
        System.out.println(uniquePaths(m , n));
    }
}
