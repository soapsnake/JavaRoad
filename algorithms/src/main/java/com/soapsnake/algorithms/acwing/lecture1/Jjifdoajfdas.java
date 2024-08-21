package com.soapsnake.algorithms.acwing.lecture1;

public class Jjifdoajfdas {

    public static boolean increasingTriplet(int[] nums) {
        //如果暴力怎么算了？
        //固定每个i，对i后面的进行双指针？
        int n = nums.length;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            int l = i + 1;
            int r = n - 1;
            while(l < r) {
                if(nums[l] > nums[i] && nums[r] > nums[l]) {
                    return true;
                }
                l++;
                r--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,5,0,4,1,3};
        System.out.println(increasingTriplet(nums));
    }
}
