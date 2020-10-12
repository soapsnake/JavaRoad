package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * Created on 2020-01-13
 */
public class Question373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //初始化一个优先级队列
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> a[0] + a[1] - (b[0] + b[1]));
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0)
            return res;
        for (int i = 0; i < nums1.length && i < k; i++) {
            //插到最后边然后进行上浮到合适位置
            que.offer(new int[] {nums1[i], nums2[0], 0});  //all cur[2] are init to 0!
        }

        while (k-- > 0 && !que.isEmpty()) {
            int[] cur = que.poll();  //弹出堆顶(最小值),然后把最后一个元素挪动到堆顶并且sink
            res.add(Arrays.asList(cur[0], cur[1]));  //最小的一对
            if (cur[2] == nums2.length - 1)
                continue;
            que.offer(new int[] {cur[0], nums2[cur[2] + 1], cur[2] + 1});
            System.out.println(que);
        }
        return res;
    }

    public static void main(String[] args) {

        Question373 question373 = new Question373();
        int[] nums1 = {1,7,11};
        int[] nums2 = {3,4,6};
        int k = 3;
        System.out.println(question373.kSmallestPairs2(nums1, nums2, k));
    }


    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }
        Queue<int[]> que = new PriorityQueue<>();
        for (int i = 0; i < nums1.length && i < k; i++) {
            que.offer(new int[] {nums1[i], nums2[0], 0});
        }
        while (k-- > 0 && !que.isEmpty()) {
            int[] cur = que.poll();
            res.add(Arrays.asList(cur[0], cur[1]));
            if (cur[2] == nums2.length - 1) {
                continue;
            }
            que.offer(new int[]{cur[0], nums2[cur[2] + 1], cur[2] + 1});
        }
        return res;
    }















    }
