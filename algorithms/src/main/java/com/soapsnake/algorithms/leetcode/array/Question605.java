package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018-12-27 00:17
 */
public class Question605 {

    public static void main(String[] args) {
        Question605 question605 = new Question605();
        int[] flowBed = {0, 1, 0};
        int n = 1;
        System.out.println(question605.canPlaceFlowers(flowBed, n));
    }

    //题目看着简单,其实做起来一点不简单,边界值特别容易出问题
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        if (flowerbed == null) {
            return false;
        }
        if (flowerbed.length == 1 && flowerbed[0] == 0) {
            return true;
        }

        for (int i = 0; i < flowerbed.length; i++) {
            //数组第一个元素必须特殊处理
            if (i == 0) {
                if (flowerbed[i] == 0 && flowerbed[i + 1] != 1) {
                    n -= 1;
                    if (n == 0) {
                        return true;
                    }
                    flowerbed[i] = 1;
                    continue;
                } else {
                    continue;
                }
            }
            //最后一个元素也必须特殊处理
            if (i == flowerbed.length - 1 && flowerbed[i] == 0 && flowerbed[i - 1] != 1) {
                n--;
                return n == 0;  //已到数组尾部,不需要再继续了
            }

            //指针在中间
            if (flowerbed[i] != 1 && flowerbed[i - 1] != 1 && flowerbed[i + 1] != 1) {
                n--;
                flowerbed[i] = 1;
            }
            if (n == 0) {
                return true;
            }
        }
        return false;
    }
}
