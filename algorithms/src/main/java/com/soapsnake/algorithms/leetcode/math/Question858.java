package com.soapsnake.algorithms.leetcode.math;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-11-17
 */
public class Question858 {

    public int mirrorReflection(int p, int q) {
        while (p % 2 == 0 && q % 2 == 0) {
            p /= 2;
            q /= 2;
        }
        if (p % 2 == 0) {
            return 2;
        } else if (q % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
