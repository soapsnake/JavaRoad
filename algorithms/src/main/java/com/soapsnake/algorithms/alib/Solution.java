package com.soapsnake.algorithms.alib;

import java.util.Arrays;

public class Solution {

    /**
     * 寻找数组中重复和缺少的数字
     * @return
     */
    public static int[] findDupAndLose(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] res = new int[2];
        Arrays.sort(arr);
        int dupIndex = -1;
        int dup = 0, lose = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                dup = arr[i];  //发现了重复数
                if (dupIndex != -1) {
                    throw new RuntimeException("多与两个数字重复");
                }
                dupIndex = i;
            } else if (arr[i] != (arr[i - 1] + 1)) {
                lose = arr[i - 1] + 1;
            }
        }
        if (dupIndex != -1) {
            res[0] = dup;
            res[1] = lose;
            return res;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 5 ,2 ,2,2};
        System.out.println(Arrays.toString(findDupAndLose(arr)));  //{2, 4}
    }

}
