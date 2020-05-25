package com.soapsnake.algorithms.leetcode.array;

import java.util.List;

/**
 *
 * Created on 2020-04-13
 */
public class Question539 {

    //leetcode539, timePoints会是多个时间,需要找到他们的最短间隔时间
    public int findMinDifference(List<String> timePoints) {
        //一天只会有24 * 60个分钟点
        boolean[] mark = new boolean[24 * 60];
        for (String time : timePoints) {
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if (mark[h * 60 + m]) {
                //其中两个时间是一样的情况
                return 0;
            }
            mark[h * 60 + m] = true;
        }

        int prev = 0, min = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        for (int i = 0; i < 24 * 60; i++) {

            if (mark[i]) {
                if (first != Integer.MAX_VALUE) {
                    min = Math.min(min, i - prev);
                }
                first = Math.min(first, i);
                last = Math.max(last, i);
                prev = i;
            }
        }
        return Math.min(min, (24 * 60 - last + first));
    }

    /**
     * A left shift by 1 means remove the first character of s and append it to the end.
     * 左shift1, 意思是拿掉s的第一个字符并且追加到最后
     * Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
     * 右shift1,意思是拿掉s的最后一个字符并且追加到最前
     */
    public  static  String stringShift(String s, int[][] shift) {
        int step = shift.length;
        for (int i = 0; i < step; i++) {
            int[] temp = shift[i];
            int amount = temp[1];
            StringBuilder stringBuilder = new StringBuilder();
            if (temp[0] == 0) {
                //左shift
                String left = s.substring(amount);
                stringBuilder.append(left);
                String remove = s.substring(0, amount);
                stringBuilder.append(remove);
                s = stringBuilder.toString();
            } else {
                //右
                String cut = s.substring(s.length() - amount);
                stringBuilder.append(cut);
                String left = s.substring(0, s.length() - amount);
                stringBuilder.append(left);
                s = stringBuilder.toString();
            }
        }
        return s;
    }

    public static void main(String[] args) {
        String s = "abc";
        int[][] shift = {{0,1}, {1,2}};
        System.out.println(stringShift(s, shift));
    }
}
