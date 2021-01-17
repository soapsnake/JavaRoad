package com.soapsnake.algorithms.leetcode.tree.segmenttree;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-01-11
 */
public class Question1649 {

    /**
     * 线段树
     */
    int[] c;
    public int createSortedArray(int[] instructions) {
            int res = 0, n = instructions.length, mod = (int)1e9 + 7;
            c = new int[100001];
            for (int i = 0; i < n; ++i) {
                res = (res + Math.min(get(instructions[i] - 1), i - get(instructions[i]))) % mod;
                update(instructions[i]);
            }
            return res;
        }

        public void update(int x) {
            while (x < 100001) {
                c[x]++;
                x += x & -x;
            }
        }

        public int get(int x) {
            int res = 0;
            while (x > 0) {
                res += c[x];
                x -= x & -x;
            }
            return res;
        }
}
