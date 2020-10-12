package com.soapsnake.algorithms.leetcode.array;

/**
 * @author
 * Created on 2019-12-30
 */
public class Question278 {

    public int firstBadVersion(int n) {
            return helper(1, n);
        }

        public int helper(int low, int high) {
            if (low >= high) return low;
            else {
                int mid = low + (high - low) / 2;
                if (isBadVersion(mid)) return helper(low, mid);
                else return helper(mid+1, high);
            }
        }

    private boolean isBadVersion(int mid) {
        return false;
    }
}
