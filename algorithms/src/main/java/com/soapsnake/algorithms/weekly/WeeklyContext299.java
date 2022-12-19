package com.soapsnake.algorithms.weekly;

import java.util.Arrays;

public class WeeklyContext299 {
    

    public static void main(String[] args) throws Exception {
        WeeklyContext299 w = new WeeklyContext299();

        //500478595
        // System.out.println(w.countHousePlacements(1000));
        // Assert.assertEquals(w.countHousePlacements(1000), 500478595);




       int[] nums1 =  {60,60,60};
        int[] nums2 = {10,90,10};
        System.out.println(w.maximumsSplicedArray(nums1, nums2));
    }

    public int countHousePlacements(int n) {
        long res = fetch(n);
        // System.out.println("fdasfdsa:  " + res);

        long tmp = (res % mod) * (res % mod);

        return (int) (tmp % mod);
    }
    static int mod = (int) 1e9+7;
    
    private static long fetch(int n) {
        long first = 0;
        long second = 1;
        while (n-- >= 0) {
            long third = (first + second) % mod;
            first = second;
            second = third;
        }
        return second;
    }

    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        // 思路: 算三部分的和, 暴力加, 前缀和
        int n = nums1.length;
        int[] pre1 = new int[n];
        pre1[0] = nums1[0];
        for (int i = 1; i < n; i++) {
            pre1[i] = pre1[i - 1] + nums1[i];
        }
        // System.out.println(Arrays.toString(pre1));

        int[] pre2 = new int[n];
        pre2[0] = nums2[0];
        for (int i = 1; i < n; i++) {
            pre2[i] = pre2[i - 1] + nums2[i];
        }

        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = nums2[i] - nums1[i];
        }

        int l = 0, r = 0;
        int cursum = 0;
        System.out.println(Arrays.toString(sum));
        for (int i = 0; i < n; i++) {
            cursum += sum[i];
            if (cursum < 0) {
                if (sum[i] > cursum) {
                    cursum = sum[i];
                    l = i - 1;
                    r = i;
                }
            } else {
                cursum += sum[i];
                r = i;
            }
        }
        System.out.println("l = " + l + " r =" + r);
        

        // System.out.println(Arrays.toString(pre2));
        // int ans = Integer.MIN_VALUE;
        // for (int left = 0; left < n; left++ ) {
        //     for (int right = left; right < n; right++ ) {
        //         int total1 = pre1[left] + (pre2[right] - pre2[left]) + pre1[n - 1] - pre1[right];
        //         int total2 = pre2[left] + (pre1[right] - pre1[left]) + pre2[n - 1] - pre2[right];
        //         System.out.println("total1 = " + total1 + "   total2 = " + total2);
        //         ans = Math.max(ans, Math.max(total1, total2));
        //     }
        // }
        System.out.println(pre2[r] - pre2[l]);
        return pre1[l] + (pre2[r] - pre2[l]) + (pre1[n - 1] - pre1[r]);
    }



}
