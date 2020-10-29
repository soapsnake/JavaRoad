package com.soapsnake.algorithms.leetcode.array;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-10 13:55
 */
public class Question849 {
    public static void main(String[] args) {
        Question849 question849 = new Question849();
        int[] seats = {1, 0, 0, 1};
        System.out.println(question849.maxDistToClosest(seats));
    }


    public int maxDistToClosest(int[] seats) {
        if (seats == null || seats.length == 1) {
            return 0;
        }
        int maxlen = 0;
        int temp = 0;
        boolean edge = false;
        for (int i = 0; i < seats.length; i++) {
            //如果是在座位的两端,这种时候的最大距离就是temp,否则的话是temp的一半
            if (i == 0 && seats[0] == 0 || i == seats.length - 1 && seats[seats.length - 1] == 0) {
                edge = true;
            }
            if (seats[i] == 0) {
                temp++;
            } else {
                if (edge) {
                    maxlen = Math.max(maxlen, temp);
                } else {
                    maxlen = Math.max(maxlen, (temp + 1) / 2);
                }
                temp = 0;
                edge = false;
            }
        }
        if (edge) {
            maxlen = Math.max(maxlen, temp);
        }
        return maxlen;
    }
}
