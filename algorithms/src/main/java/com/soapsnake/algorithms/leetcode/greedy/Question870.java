package com.soapsnake.algorithms.leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-03-25
 */
public class Question870 {

    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        int n = A.length;
        int[] res = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < n; i++) {
            pq.add(new int[]{B[i], i});   //数组长度为2, 0:value -> 1:index,按value从大到小排序
        }
        int lo = 0, hi = n - 1;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();  //value -> index
            int idx = cur[1];
            int val = cur[0];

            //注意了:A已经从小到大排过序,所以这里A[hi]取的是A中的最大值
            //贪心思想:每一轮比较,如果A中还没有用过的最大数比当前轮B最大数字要大,那我就用掉这个最大数,
            // 如果没有,那我就田忌赛马,用我最弱的数字去消除你最大的数字,(类似军旗中小兵碰司令,有点不妥,但是能理解就行)
            if (A[hi] > val) {
                res[idx] = A[hi--];
            } else {
                res[idx] = A[lo++];
            }
        }
        return res;
    }
}
