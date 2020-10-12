package com.soapsnake.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * Created on 2020-07-03
 */
public class Question957 {

    //leetcode957
    public int[] prisonAfterNDays(int[] cells, int N) {
        if (cells == null || cells.length == 0 || N <= 0) {
            //边界条件, cells为空,N不合法
            return cells;
        }
        boolean hasCycle = false;  //是否循环?
        int cycle = 0;  //轮次
        HashSet<String> set = new HashSet<>();  //缓存,缓存啥?
        for (int i = 0; i < N; i++) { //遍历牢房
            int[] next = nextDay(cells);  //计算牢房第二天会变成啥样
            //把第二天的牢房的状态进行序列化
            String key = Arrays.toString(next);
            if (!set.contains(key)) { //store cell state
                //如果没法发现相同的牢房状态,那么就放进去
                set.add(key);
                cycle++;  //cycle记录的其实是缓存的状态数量,也就是组合数量
            } else { //hit a cycle
                //命中了一个已经存在的牢房状态,这里终止的原因是不会再出现新的牢房状态了吗?
                hasCycle = true;  //探测到了重复,可以直接退出了,因为以后会一直重复这个过程,没必要再继续算了
                break;
            }
            cells = next;
        }

        if (hasCycle) {
            //这个取余运算的真实含义是:不管N多大,都只会一直重复set中的cycle种模式(cycle其实是模式的数量,也是set的size())
            //对cycle取余后,就是去除重复后,真实需要改变牢房的次数
            N %= cycle;
            for (int i = 0; i < N; i++) {
                cells = nextDay(cells);
            }
        }
        return cells;
    }

    private int[] nextDay(int[] cells) {
        int[] tmp = new int[cells.length]; //创建一个牢房副本
        for (int i = 1; i < cells.length - 1; i++) {
            //遍历原始牢房,根据规则计算牢房副本的每件牢房的下一个状态
            tmp[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return tmp;
    }
}
