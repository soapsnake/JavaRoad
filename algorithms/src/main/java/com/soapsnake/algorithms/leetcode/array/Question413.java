package com.soapsnake.algorithms.leetcode.array;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-02-23
 */
public class Question413 {

    //leetcode413
    //数组的等差数列子数组的个数
    public int numberOfArithmeticSlices(int[] A) {
        int curr = 0, total = 0;
        for (int i = 2; i < A.length; i++)
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                //发现 i-2,i-1,i是一个等差数列
                curr += 1;
                total += curr;  //感觉这里有斐波拉契数列的意思
            } else {
                curr = 0;
            }
        return total;
    }
}
