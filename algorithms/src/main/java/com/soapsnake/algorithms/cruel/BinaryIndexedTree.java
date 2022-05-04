package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-02-10
 * JavaRoad
 */
public class BinaryIndexedTree {

    private int[] index;
    

    public static void main(String[] args) {
    
    }
    public List<Integer> countSmallerer(int[] nums) {
        System.out.println("消息来自新的键盘!!!!!");
        
        return null;
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] p = new int[n];
        for (int i = 1; i < n; ++i) {
            p[nums1[i]] = i;
        }
        long ans = 0L;
        int[] tree  = new int[n + 1];
        for (int i = 1; i < n - 1; ++i) {
            for (int j = p[nums2[i - 1]] + 1; j <= n; j += j  & -j) {
                ++tree[j];
            }
            int y  = p[nums2[i]];
        int less = 0;
        for (int j = y; j > 0; j &= j - 1) {
            less += tree[j];
        }
        ans += (long) less * (n - 1 - y - (i - less));
        }
        return ans;
    }

    public long goodTriplets2(int[] nums1, int[] nums2) {
    int n = nums1.length;
        int[] p = new int[n];
        for (int i = 0; i < n; ++i)
            p[nums1[i]] = i;
        long ans = 0L;
        int[] tree = new int[n + 1];
        for (int i = 1; i < n - 1; ++i) {
            for (int j = p[nums2[i - 1]] + 1; j <= n; j += j & -j) // 将 p[nums2[i-1]]+1 加入树状数组
                ++tree[j];
            int y = p[nums2[i]];
            int less = 0;
            for (int j = y; j > 0; j &= j - 1) // 计算 less
                less += tree[j];
            ans += (long) less * (n - 1 - y - (i - less));
        }
        return ans;
}
}
