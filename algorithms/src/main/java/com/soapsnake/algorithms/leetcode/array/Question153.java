package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Question153 {

    /**
     * 升序数组旋转后的最小值
     * Example 1:
     * Input: [3,4,5,1,2]
     * Output: 1
     * <p>
     * Example 2:
     * Input: [4,5,6,7,0,1,2]
     * Output: 0
     */
    //遍历傻逼向
    public int findMin(int[] nums) {
        int min = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] < nums[i - 1]) {
                min = Math.min(min, nums[i]);
            }
        }
        return min;
    }

    //二分高手向
    //leetcode153旋转数组最小值,这个不画图很不好解
    public int findMin2(int[] nums) {
        //把0号元素标记为最小
        int min = nums[0];

        //边界条件
        if (nums.length == 1) {
            return nums[0];
        }
        //左右指针
        int left = 0;
        int right = nums.length - 1;
        while (right > left) {
            int mid = (left + right) / 2;
            if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
                //如果mid指针指向的值小于前面一个元素,说明这个元素就是折断前的最小值(画个图很容易理解)
                return nums[mid];
            }
            if (nums[mid] >= nums[left] && nums[mid] > nums[right]) {
                //这种情况,mid指针实际上落在了折断后更大数的区间例如4,5,6,7,0,1,2 mid指向7
                left = mid + 1;
            } else {
                //这种情况,mid指针实际上落在了折断后更小数的区间
                right = mid - 1;
            }
        }
        return nums[left];  //为什么要是nums[left]了?
    }

    public static void main(String[] args) {
//        List asr = new ArrayList();
//        asr.add("123");
//        asr.add(1);
//        System.out.println(asr.get(0) instanceof  Object);


        String messa = "Hello world!";

        String newmse = messa.substring(6, 12) + messa.substring(12, 6);
        System.out.println(newmse );

    }

    private static Exception print(int i) {
        if (i > 0) {
            return new Exception();
        }
        return null;
    }


}
