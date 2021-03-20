package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soapsnake
 * @date 2018/10/27
 * <p>
 * Given a string S and a character C,
 * return an array of integers representing the shortest distance from the character C in the string.
 */
class Question821 {

//    public static void main(String[] args) {
//        String s = "loveleetcode";
//        char c = 'e';
//        Question821 question821 = new Question821();
//        ArrayUtils.printArr(question821.shortestToChar(s, c));
//    }

    //leetcode821
    public int[] shortestToChar(String s, char c) {
        char[] chars = s.toCharArray();
        int[] distances = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                distances[i] = 0;
                continue;
            }
            //左向指针,往头遍历
            int leftDist = Integer.MAX_VALUE;
            for (int left = i; left >= 0; left--) {
                if (chars[left] == c) {
                    leftDist = i - left;
                    break;
                }
            }

            //右向指针,往尾部遍历
            int rightDist = Integer.MAX_VALUE;
            for (int right = i; right < chars.length; right++) {
                if (chars[right] == c) {
                    rightDist = right - i;
                    break;
                }
            }
            distances[i] = Math.min(leftDist, rightDist);
        }

        return distances;
    }

    //A的所有连续子数组中等差数列的个数,必须最少3个元素
    public int numberOfArithmeticSlices(int[] A) {
        int curr = 0, sum = 0;
        for (int i = 2; i < A.length; i++)
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                //如果是等差的
                curr += 1;
                sum += curr;
            } else {
                curr = 0;
            }
        return sum;
    }

    //A的所有连续子数组中等差数列的个数,必须最少3个元素
    public int numberOfArithmeticSlices2(int[] A) {
        if (A.length < 3) {
            return 0;
        }

        List<List<Integer>> all = new ArrayList<>();
        this.helper(A, new ArrayList<>(), 0, all);
        System.out.println(all);
        return all.size();
    }

    private void helper(int[] a, List<Integer> temp, int start, List<List<Integer>> all) {
        if (temp.size() >= 3) {
            if (this.isAritheme(temp)) {
                all.add(new ArrayList<>(temp));
            }
        }
        for (int i = start; i < a.length; i++) {
            if (temp.contains(a[i])) {
                continue;
            }
            temp.add(a[i]);
            helper(a, temp, start + 1, all);
            temp.remove(temp.size() - 1);
        }
    }

    //判断是不是等差数列
    private boolean isAritheme(List<Integer> temp) {
        if (temp.size() < 3) {
            return false;
        }
        int diff = temp.get(1) - temp.get(0);
        for (int i = 1; i < temp.size() - 1; i++) {
            if (temp.get(i + 1) - temp.get(i) != diff) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        Question821 question821 = new Question821();
        System.out.println(question821.numberOfArithmeticSlices(a));
    }

}
