package com.soapsnake.algorithms.leetcode.array;

/**
 * 
 * Created on 2020-11-17
 */
public class Question845 {

    //基本思路,左右指针,左指针先移动,到达第一个上升处时,left停住,right开始爬坡
    //right到顶之后,开始下坡,一边下坡一边计算Math.max最大长度
    //right下坡停止(重新开始上坡时),移动left到right位置,left继续之前的过程,开始下一轮爬坡
    public int longestMountain(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int max = 0;
        while (left < A.length - 2) {
            while (left < A.length - 1 && A[left] >= A[left + 1]) {
                left++;
            }
            right = left + 1;
            //爬坡
            while (right < A.length - 1 && A[right] < A[right + 1]) {
                right++;
            }
            //到顶了,开始下坡
            while (right < A.length - 1 && A[right] > A[right + 1]) {
                right++;
                max = Math.max(right - left + 1, max);
            }
            //下坡结束,left归位,开始下一轮
            left = right;
        }
        return max;
    }
}
