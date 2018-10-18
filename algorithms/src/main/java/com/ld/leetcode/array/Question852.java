package com.ld.leetcode.array;


public class Question852 {

    //O(n) 级别算法
    public int peakIndexInMountainArray(int[] A) {
        for (int i=0;i<A.length;i++){
            if (A[i] < A[i+1] && A[i+1] > A[i+2]){
                return i+1;
            }
        }
        return 0;
    }

    //todo O(logn)级别算法,二分查找的思想
    public int peakIndexInMountainArray2(int[] A){
        int lo = 0;
        int hi = A.length - 1;
        while (lo < hi){
            int mid = (hi + lo) / 2;
            if (A[mid] < A[mid+1]){  //要找的点肯定在mid的右侧
                lo = mid;
            }else if (A[mid] < A[mid - 1]){  //要找的点肯定在mid的左侧
                hi = mid;
            }else {
                return mid;
            }
        }
        return 0;
    }



    public static void main(String[] args) {
        Question852 question852 = new Question852();
        int[] a = {3,4, 5, 1};
        System.out.println(question852.peakIndexInMountainArray2(a));
    }

}
