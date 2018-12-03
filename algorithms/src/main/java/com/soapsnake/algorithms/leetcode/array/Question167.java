package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/3 22:55
 */
public class Question167 {

    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        for (int left = 0, right = numbers.length - 1; left < right; ) {
            if (numbers[left] + numbers[right] == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                break;
            }

            if (numbers[left] + numbers[right] > target) {
                right--;
                continue;
            }
            if (numbers[left] + numbers[right] < target) {
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Question167 question167 = new Question167();
        int[] e = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(question167.twoSum(e, target)));
    }
}
