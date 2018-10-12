package com.ld.leetcode.array;

import com.ld.leetcode.list.ArrayUtils;

/**
 * Given an array A of non-negative integers,
 * return an array consisting of all the even elements of A,
 * followed by all the odd elements of A.
 *
 * Input: [3,1,2,4]
 * Output: [2,4,3,1]
 */
public class Question905 {

    //快排版思路
    public int[] sortArrayByParity(int[] A) {
        int middleIndex = A.length / 2;

        int left = 0;
        int right = A.length - 1;

        for (int i =0; ;i++){
            if (A[left] % 2 == 0){
                left++;
            }

            //left指针指向奇数了
            if (A[right] % 2 != 0){
                right--;
            }
            //right此时指向偶数了
            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;

            if (left == right){
                break;
            }
        }
        return A;
    }

    //额外数组,是奇数就插到新数组右侧,是偶数就插到左侧
    public int[] sortArrayByParity2(int[] A) {
        int[] res = new int[A.length];
        int left = 0;
        int right = A.length - 1;
        for (int i=0;i<A.length;i++){
            if (A[i] % 2 == 0){
                res[left] = A[i];
                ++left;
            }else {
                res[right] = A[i];
                --right;
            }
        }
        return  res;
    }

        public static void main(String[] args) {
        Question905 question905 = new Question905();
        int[] arr = {3,1,2,4};
        ArrayUtils.printArr(question905.sortArrayByParity2(arr));
    }
}
