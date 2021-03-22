package com.soapsnake.algorithms.leetcode.math;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * Created on 2020-04-02
 */
public class Question519 {
    //leetcode519
    static class Solution {
        private Map<Integer, Integer> map;
        private int rows, cols, total;
        private Random rand;

        public Solution(int n_rows, int n_cols) {
            map = new HashMap<>();
            rand = new Random();
            rows = n_rows;
            cols = n_cols;
            total = rows * cols;  //格子总数
        }

        public int[] flip() {
            int r = rand.nextInt(total--);   //剩下的格子数里面挑一个,r的总数量和格子一致
            int x = map.getOrDefault(r, r);     //r
            map.put(r, map.getOrDefault(total, total));
            return new int[]{x / cols, x % cols};
        }

        public void reset() {
            map.clear();
            total = rows * cols;
        }
    }
}
