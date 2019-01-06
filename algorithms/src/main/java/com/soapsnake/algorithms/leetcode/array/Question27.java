package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-06 21:27
 */
public class Question27 {

    //智障级解法!
    public int removeElement(int[] nums, int val) {
        if (nums == null) {
            return 0;
        }
        int newlen = nums.length;
        int conf = val - 100;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                newlen--;
            }
        }
        if (newlen == 0) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] == val) {
                if (i == nums.length - 1) {
                    nums[nums.length - 1] = conf;
                    break;
                }
                for (int j = i; j < nums.length - 1; j++) {
                    nums[j] = nums[j + 1];
                    if (nums[nums.length - 1] == val) {
                        nums[nums.length - 1] = conf;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(nums));
        return newlen;
    }

    public static void main(String[] args) {
        Question27 question27 = new Question27();
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        System.out.println(question27.removeElement2(nums, val));
        System.out.println(Arrays.toString(nums));
    }

    //大神级解法,没看懂囧
    public int removeElement2(int[] A, int elem) {
        int m = 0;
        for(int i = 0; i < A.length; i++){

            if(A[i] != elem){
                A[m] = A[i];
                m++;
            }
        }
        return m;
    }
}
