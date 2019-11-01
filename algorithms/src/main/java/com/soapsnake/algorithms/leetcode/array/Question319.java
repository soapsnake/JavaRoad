package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;

public class Question319 {

    public static void main(String[] args) {
        Question319 question319 = new Question319();
        int n = 3;
        System.out.println(question319.bulbSwitch(n));
    }

    public int bulbSwitch2(int n) {
        return (int) Math.sqrt(n);
    }

    public int bulbSwitch(int n) {
        int[] bulbs = new int[n];
        Arrays.fill(bulbs, 1);
        int step = 0;
        for (int i = 0; i < bulbs.length; i++) {
            ++step;
            for (int k = 0 + step; k < n; k += step) {
                if (bulbs[k] == 1)
                    bulbs[k] = 0;
                else
                    bulbs[k] = 1;
            }
            System.out.println(Arrays.toString(bulbs));
        }
        int res = 0;
        for (int i = 0; i < bulbs.length; i++) {
            if (bulbs[i] == 1)
                res++;
        }
        return res;
    }
}
