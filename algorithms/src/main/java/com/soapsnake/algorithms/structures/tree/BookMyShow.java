package com.soapsnake.algorithms.structures.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-06-10
 * JavaRoad
 */
public class BookMyShow {

    private final int n, m;
    private final int[] max;
    private final long[] total;
    private final int[] numZerosRight, numZerosLeft;

    public BookMyShow(int n, int m) {
        this.n = nextPow2(n);
        this.m = m;
        this.max = new int[this.n * 2 - 1];
        this.total = new long[this.n * 2 - 1];
        this.numZerosRight = new int[this.n + 2];
        this.numZerosLeft = new int[this.n + 2];
        Arrays.fill(max, this.n - 1, this.n + n - 1, m);
        Arrays.fill(total, this.n - 1, this.n + n - 1, m);

        for (int i = this.n - 2, i1 = i * 2 + 1, i2 = i * 2 + 2; i >= 0; i--, i1 -= 2, i2 -= 2) {
            max[i] = Math.max(max[i1], max[i2]);
            total[i] = total[i1] + total[i2];
        }
    }

    public int[] gather(int k, int maxRow) {
        int mostLeft = mostLeft(0, 0, n, k, maxRow + 1);
        if (mostLeft == -1) return new int[0];

        int v = n - 1 + mostLeft;
        int[] ans = {mostLeft, m - max[v]};

        max[v] -= k;
        total[v] -= k;

        while (v != 0) {
            v = (v - 1) / 2;
            max[v] = Math.max(max[v * 2 + 1], max[v * 2 + 2]);
            total[v] = total[v * 2 + 1] + total[v * 2 + 2];
        }
        return ans;
    }

    private int mostLeft(int v, int l, int r, int k, int qr) {
        if (l >= qr || max[v] < k) return -1;
        if (l == r - 1) return l;
        int mid = (l + r) / 2;
        int left = mostLeft(v * 2 + 1, l, mid, k, qr);
        if (left != -1) return left;
        return mostLeft(v * 2 + 2, mid, r, k, qr);
    }

    public boolean scatter(int k, int maxRow) {
        long sum = total(0, 0, n, maxRow + 1);
        if (sum < k) return false;
        int i = 0;

        Deque<Integer> deque = new ArrayDeque<>();
        while (k != 0) {
            i = i + numZerosRight[i] + 1;
            int v = n - 1 + i - 1;
            int spent = Math.min(k, max[v]);
            k -= spent;
            max[v] -= spent;
            total[v] -= spent;
            if (max[v] == 0) {
                numZerosRight[i - numZerosLeft[i] - 1] += numZerosRight[i] + 1;
                numZerosLeft[i + numZerosRight[i] + 1] += numZerosLeft[i] + 1;
            }
            if (v != 0) {
                v = (v - 1) / 2;
                if (deque.isEmpty() || deque.peekLast() != v) deque.addLast(v);
            }
        }

        while (!deque.isEmpty()) {
            int v = deque.pollFirst();
            max[v] = Math.max(max[v * 2 + 1], max[v * 2 + 2]);
            total[v] = total[v * 2 + 1] + total[v * 2 + 2];
            if (v != 0) {
                v = (v - 1) / 2;
                if (deque.isEmpty() || deque.peekLast() != v) deque.addLast(v);
            }
        }
        return true;
    }
    private long total(int v, int l, int r, int qr) {
        if (l >= qr) return 0;
        if (r <= qr) return total[v];
        int mid = (l + r) / 2;
        return total(v * 2 + 1, l, mid, qr) + total(v * 2 + 2, mid, r, qr);
    }

    private static int nextPow2(int n) {
        if ((n & (n - 1)) == 0) return n;
        else return Integer.highestOneBit(n) << 1;
    }
}
