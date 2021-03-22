package com.soapsnake.algorithms.leetcode.math;

import java.util.Arrays;

/**
 *
 * Created on 2020-03-27
 */
public class Question556 {

    //leetcode556
    public int nextGreaterElement(int n) {
        char[] number = (n + "").toCharArray();
        int i, j;
        //1.从最右侧的数字开始往左扫描,找到第一对两个连着的数字,左边小于右边的数字对
        for (i = number.length-1; i > 0; i--)
            if (number[i-1] < number[i])
                break;

        //如果没有这样的数字对,比如21,表名这个数字不可能出现题目要求的结果
        if (i == 0) {
            return -1;
        }

        //2.找出第i-1个数字右侧所有大于number[i-1]的数字中的最小的那个
        int x = number[i-1], smallest = i;
        for (j = i+1; j < number.length; j++)
            if (number[j] > x && number[j] <= number[smallest])
                smallest = j;

        //3.交换上述找出来的最小数字和第i-1个数字
        char temp = number[i-1];
        number[i-1] = number[smallest];
        number[smallest] = temp;

        //4.将i-1后的数字进行升序排列,Arrays.sort可以只对一个数组的一部分进行排序
        Arrays.sort(number, i, number.length);

        //严谨性!!!
        long val = Long.parseLong(new String(number));
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;
    }

    public static void main(String[] args) {
        Question556 question556 = new Question556();
        System.out.println(question556.nextGreaterElement(12));
    }
}
