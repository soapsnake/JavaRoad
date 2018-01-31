package com.ld.algorithms;

/**
 * Created by liudun on 2018/1/28.
 */
public class FastSortV2 {

    public static int[] sort(int[] numbers, int left, int right){

        int temp = 0;
        int key = left;
        int i = left;
        int j = right;

        //左右指针相撞时发生分裂,注意此处i一定要小于j
        while (i < j) {
            //发现有小于中间值的情况时退出
            while (numbers[i] < numbers[key] && i < j) {
                i++;
            }
            temp = numbers[i];
            //发现有大于中间值的情况时退出
            while (numbers[j] > numbers[key] && i < j) {
                j--;
            }
            numbers[i] = numbers[j];
            numbers[j] = temp;
            System.out.println("tuichu");
        }
        numbers[i] = numbers[key];

        if (i-1>left)sort(numbers,left,i - 1);
        if (right > i+1)sort(numbers,i+1, right);
        return numbers;
        }


}
