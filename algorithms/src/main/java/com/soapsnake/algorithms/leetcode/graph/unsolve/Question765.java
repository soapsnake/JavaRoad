package com.soapsnake.algorithms.leetcode.graph.unsolve;

import com.soapsnake.algorithms.structures.unionfind.DSU;

/**
 * }
 * Created on 2020-07-11
 */
public class Question765 {

    /**
     * N对情侣坐在排列成一排的2N个座位上，想牵手。我们想知道最少的交换次数，
     * 使每对情侣都是并排而坐。一个交换包括选择任意两个人，然后他们站起来并交换座位。
     * 人和座位用一个从0到2N-1的整数来表示，情侣的编号依次为，
     * 第一对情侣是（0，1），第二对情侣是（2，3），以此类推，最后一对情侣是（2N-2，2N-1）。
     *
     * 情侣的初始座位由row[i]是最初坐在第i-th座位上的人的值给出。
     * @param row
     * @return
     */
    //没有搞懂啊!!!
    public int minSwapsCouples(int[] row) {
        //N = 情侣的对数
        int N = row.length/ 2;
        DSU uf = new DSU(N);
        for (int i = 0; i < N; i++) {
            int a = row[2*i];
            int b = row[2*i + 1];
            uf.union(a/2, b/2);
        }
        //结果等于情侣对数 - 孤点的数量
        return N - uf.getCount();
    }
}
