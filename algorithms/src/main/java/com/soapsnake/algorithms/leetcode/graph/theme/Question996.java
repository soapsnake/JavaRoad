package com.soapsnake.algorithms.leetcode.graph.theme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.jar.JarEntry;

import org.junit.Test;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-08-02
 */
public class Question996 {

    //leetcode996
    public int numSquarefulPerms(int[] A) {
        Arrays.sort(A);
        helper(new ArrayList<>(), A, new boolean[A.length], -1);
        return count;
    }

    private int count = 0;
    //事实上是backtrace算法
    private void helper(List<Integer> temp, int[] A, boolean[] used, int lastNumber) {
        if (temp.size() == A.length) {
            count++;
        } else {
            for (int i = 0; i < A.length; i++) {
                if (used[i] || (i > 0 && A[i] == A[i - 1] && !used[i - 1]))
                    continue;
                if (lastNumber != -1) {
                    // if we cant form a square we can not proceed to form a squareful array
                    if (!isSquare(A[i], lastNumber))
                        continue;
                }
                used[i] = true;
                temp.add(A[i]);
                helper(temp, A, used, A[i]);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }

    private boolean isSquare(int a, int b) {
        double sqr = Math.sqrt(a + b);
        return (sqr - Math.floor(sqr)) == 0;
    }


    public int numSquarefulPerms2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(A);
        Set<int[]> cache = new HashSet<>();
        int res = 0;
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            for (int k = 0; k < curSize; k++) {
                int[] cur = queue.poll();
                if (cache.contains(cur)) {
                    continue;
                }
                for (int i = 0; i < cur.length; i++) {
                    for (int j = i + 1; j < cur.length; j++) {
                        int[] swaped = this.swaped(cur, i, j);
                        cache.add(swaped);
                        if (this.isSquareFul(swaped)) {
                            res++;
                            queue.add(swaped);
                        }
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void testIssqu() {
        System.out.println(isSquareFul(new int[] {1, 17, 8}));
    }

    private boolean isSquareFul(int[] swaped) {
        System.out.println("test is squareFUl " + Arrays.toString(swaped));
        int res = 0;
        for (int i = 0; i < swaped.length - 1; i++) {
            res = swaped[i] + swaped[i + 1];
            if (!isSquare(res)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testIssqure() {
        System.out.println(numSquarefulPerms(new int[] {1, 17, 8}));
    }

    private boolean isSquare(int res) {
        if (res == 0) {
            return false;
        }
        if (res == 1) {
            return true;
        }
        return this.findMid(0, res, res);
    }

    private boolean findMid(int left, int right, int num) {
        if (left > right) {
            return false;
        }
        int mid = left + (right - left) / 2;
        if (mid < 0) {
            return false;
        }
        if (mid * mid == num) {
            return true;
        }
        if (mid > num / mid) {
            return findMid(left, mid - 1, num);
        }
        if (mid < num / mid) {
            return findMid(mid + 1, right, num);
        }
        return false;
    }

    private int[] swaped(int[] cur, int i, int j) {
        int[] res = new int[cur.length];
        System.arraycopy(cur, 0, res, 0, res.length);
        int temp = res[i];
        res[i] = res[j];
        res[j] = temp;
        return res;
    }
}
