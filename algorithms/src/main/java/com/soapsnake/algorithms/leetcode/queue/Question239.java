package com.soapsnake.algorithms.leetcode.queue;

import java.util.LinkedList;

/**
 * 
 * Created on 2020-11-15
 */
public class Question239 {

    //滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList<>();
        //窗口从左侧对准0,一直移动到右侧对准len-1,实际上窗口走的距离是(len - k + 1)
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            //队列尾部(最小值)要始终保证是大于等于nums[i]的,不能保证那就沿着队列尾向头弹(从小到大)
            //直到队列里面的值是大于等于该值为止,但是要注意了,这里存的是index,不是实际的值
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();  //移除队列尾部的值
            }
            //时刻牢记queue里存放的是index
            queue.add(i);

            //窗口向右滑动,但是queue的头的索引有可能已经不再窗口里面了,这时候要把这个老头去掉
            //窗口每滑动一次就有可能会执行一次这个操作,所以不用使用while来操作
            if (queue.peek() <= i - k) {
                queue.poll();   //移除队列头
            }

            //这个if主要是用来保证队列从第一个窗口的最右侧开始,画个图就明白了
            //窗口虽然在移动,但是索引已经被计入队列,所以只等取最大值就可以了
            if (i + 1 >= k) {
                res[i + 1 - k] = nums[queue.peek()];
            }
        }
        return res;
    }











}
