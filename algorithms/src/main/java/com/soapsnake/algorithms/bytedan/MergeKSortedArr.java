package com.soapsnake.algorithms.bytedan;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Created on 2020-10-09
 */
public class MergeKSortedArr {
    //题目:lists中的nums都是有序数组,合并这些数组,要求数组都是有序的
    public int[] mergeList(List<int[]> arrays) {
        // write your code here
        if (arrays == null || arrays.size() == 0) {
            return null;
        }
        return helper(arrays, 0, arrays.size() - 1);
    }

    private int[] helper(List<int[]> arrays, int l, int r) {
        //左右指针都指向最中间的那个数组,奇数个数组的情况下
        if (l == r) {
            return arrays.get(l);
        }
        //偶数个数组的情况下
        if (l + 1 == r) {
            return this.merge2Arrays(arrays.get(l), arrays.get(r));
        }

        //mid指针指向最中间的那个数组
        int mid = l + (r - l) / 2;
        //不断把中间的数组合并入最左侧的数组
        int[] left = helper(arrays, l, mid);

        //不断把中间的数组合并入最右侧的数组
        int[] right = helper(arrays, mid + 1, r);

        //最左侧数组已经吞并了左半区间的数组,最右数组同样,最后把这俩家伙最后做一次合并
        return this.merge2Arrays(left, right);
    }

    //专门用来合并两个数组
    private int[] merge2Arrays(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];

        int i = 0;  //a数组的指针
        int j = 0;  //b数组指针
        int k = 0;  //res数组指针
        for (; k < res.length; k++) {
            if (i >= a.length) {
                res[k] = b[j++];
            } else if (j >= b.length) {
                res[k] = a[i++];
            } else if (a[i] < b[j]) {
                res[k] = a[i++];
            } else {
                res[k] = b[j++];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            res.add(i, i);
        }
        System.out.println(res);
    }

}
