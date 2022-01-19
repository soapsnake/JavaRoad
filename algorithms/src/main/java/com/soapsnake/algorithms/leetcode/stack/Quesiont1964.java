package com.soapsnake.algorithms.leetcode.stack;

/**
 *
 * Created on 2022-01-05
 */
public class Quesiont1964 {

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int[] temp = new int[obstacles.length];
        int top = -1;
        int[] result = new int[obstacles.length];
        for (int i = 0; i < obstacles.length; i++) {
            if (top == -1 || obstacles[i] >= temp[top]) {
                temp[++top] = obstacles[i];
                result[i] = top + 1;
            } else {
                int left = 0, right = top, mid = 0;
                while (left < right) {
                    mid = left + (right - left) / 2;
                    if (temp[mid] <= obstacles[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                temp[right] = obstacles[i];
                result[i] = right + 1;
            }
        }
        return result;
    }
}
