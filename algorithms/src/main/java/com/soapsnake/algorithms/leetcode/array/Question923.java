package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * 
 * Created on 2021-03-24
 */
public class Question923 {

    //question923
    public int threeSumMulti(int[] arr, int target) {
        int MOD = 1_000_000_007;
        long ans = 0;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; ++i) {
            // We'll try to find the number of i < j < k
            // with A[j] + A[k] == T, where T = target - A[i].

            // The below is a "two sum with multiplicity".
            int T = target - arr[i];
            int j = i+1, k = arr.length - 1;

            while (j < k) {
                // These steps proceed as in a typical two-sum.
                if (arr[j] + arr[k] < T)
                    j++;
                else if (arr[j] + arr[k] > T)
                    k--;
                else if (arr[j] != arr[k]) {  // We have A[j] + A[k] == T.
                    // Let's count "left": the number of A[j] == A[j+1] == A[j+2] == ...
                    // And similarly for "right".
                    int left = 1, right = 1;
                    while (j+1 < k && arr[j] == arr[j+1]) {
                        left++;
                        j++;
                    }
                    while (k-1 > j && arr[k] == arr[k-1]) {
                        right++;
                        k--;
                    }

                    ans += left * right;
                    ans %= MOD;
                    j++;
                    k--;
                } else {
                    // M = k - j + 1
                    // We contributed M * (M-1) / 2 pairs.
                    ans += (k-j+1) * (k-j) / 2;
                    ans %= MOD;
                    break;
                }
            }
        }

        return (int) ans;
    }
}
