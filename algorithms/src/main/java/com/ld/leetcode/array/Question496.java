package com.ld.leetcode.array;

import com.ld.leetcode.list.ArrayUtils;

/**
 * @author soapsnake
 * @date 2018/11/8
 */
public class Question496 {

    //todo 这道题目并不简单
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //nums1 是 nums2的子数组,两个数组都是无序的
        int[] res = new int[nums1.length];
        int des = 0;
        int index = 0;
        for (int i = 0; i < nums2.length; i++) {
            if (index == nums1.length) {
                break;
            }
            for (int j = 0; j < nums1.length; j++) {
                if (nums2[j] == nums1[i]) {
                    des = nums1[i];
                    break;
                }
            }
            if (des == 0) {
                continue;
            }

            for (int k = i + 1; k < nums2.length; k++) {
                if (nums2[k] > des) {
                    res[index++] = nums2[k];
                }
            }
            res[index++] = -1;
        }


        return res;
    }

    public static void main(String[] args) {
        Question496 question496 = new Question496();

        int[] nums1 = {4,1,2};
        int[] nums2 = {1,2,4,2};

        ArrayUtils.printArr(question496.nextGreaterElement(nums1, nums2));
    }


}
