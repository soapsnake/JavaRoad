package com.soapsnake.algorithms.leetcode.matrix;

/**
 * 
 * Created on 2020-11-14
 */
public class Question458 {

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int pigs = 0;
        while (Math.pow(minutesToTest / minutesToDie + 1, pigs) < buckets) {
            pigs += 1;
        }
        return pigs;
    }
}
