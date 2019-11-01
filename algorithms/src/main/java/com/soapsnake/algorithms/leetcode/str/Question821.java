package com.soapsnake.algorithms.leetcode.str;

import com.soapsnake.algorithms.leetcode.array.ArrayUtils;

/**
 * @author soapsnake
 * @date 2018/10/27
 * <p>
 * Given a string S and a character C,
 * return an array of integers representing the shortest distance from the character C in the string.
 */
class Question821 {

    public static void main(String[] args) {
        String s = "loveleetcode";
        char c = 'e';
        Question821 question821 = new Question821();
        ArrayUtils.printArr(question821.shortestToChar(s, c));
    }

    public int[] shortestToChar(String S, char C) {
        char[] chars = S.toCharArray();
        int[] distances = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == C) {
                distances[i] = 0;
                continue;
            }
            //左向指针,往头遍历
            int leftDist = Integer.MAX_VALUE;
            for (int left = i; left >= 0; left--) {
                if (chars[left] == C) {
                    leftDist = i - left;
                    break;
                }
            }

            //右向指针,往尾部遍历
            int rightDist = Integer.MAX_VALUE;
            for (int right = i; right < chars.length; right++) {
                if (chars[right] == C) {
                    rightDist = right - i;
                    break;
                }
            }
            distances[i] = Math.min(leftDist, rightDist);
        }

        return distances;
    }


}
