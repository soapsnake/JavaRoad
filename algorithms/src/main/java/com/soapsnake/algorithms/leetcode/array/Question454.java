package com.soapsnake.algorithms.leetcode.array;

import java.util.HashMap;
import java.util.Map;

import com.sun.tools.javac.code.Type.ForAll;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-03-08
 */
public class Question454 {


    /**
     * Example:
     * Input:
     * A = [ 1, 2]
     * B = [-2,-1]
     * C = [-1, 2]
     * D = [ 0, 2]
     * <p>
     * Output:
     * 2
     * <p>
     * Explanation:
     * The two tuples are:
     * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     */
    //leetcode454
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : C) {
            for (int j : D) {
                map.put(i + j, map.getOrDefault(i + j, 0) + 1);
            }
        }

        int res = 0;
        for (int l : A) {
            for (int m : B) {
                res += map.getOrDefault(-(l + m), 0);
            }
        }
        return res;
    }
}
