package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

public class Question274 {

    public static void main(String[] args) {
        Question274 question274 = new Question274();
        int[] nums = {3, 0, 6, 1, 5};
        System.out.println(question274.hIndex(nums));
    }

    /**
     * Input: citations = [3,0,6,1,5]
     * Output: 3
     */
    //leetcode 274
    public int hIndex(int[] citations) {
        if (citations.length == 0) {
            return 0;
        }
        int n = citations.length;
        int[] buckets = new int[n + 1];   //桶个数可任意
        for (int c : citations) {
            if (c >= n) {
                buckets[n]++;   //最后一个桶内的数字的含义是:引用次数超过发表次数的文章的个数总数
            } else {
                buckets[c]++;  //除了最后一个桶之外,其他位置的桶内存的是引用次数小于发表文章数的次数(索引是引用次数)
            }
        }

        System.out.println(Arrays.toString(buckets));
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += buckets[i];
            if (count >= i) {  //索引的含义是某篇文章被引用的数量
                return i;
            }
        }
        return 0;
    }
}
