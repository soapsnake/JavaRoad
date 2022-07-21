package com.soapsnake.algorithms.cruel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WeeklyContext301 {
    
    public static void main(String[] args) throws Exception {
        WeeklyContext301 wek = new WeeklyContext301();
        System.out.println();
    }

    public boolean canChange(String start, String target) {
        //读题: R可以往右移动, L可以往左移动,直到变成target
        StringBuilder sb = new StringBuilder(start);
        int n = start.length();
        for(int i = 0; i < n; i++) {
            sb.charAt(i);
        }
        return false;
    }

    public int fillCups(int[] amount) {
        //原则: 每次都减最大的
        int total = 0;
        while (true) {
            Arrays.sort(amount);
            if (amount[0] == 0 && amount[1] == 0 && amount[2] == 0) {
                return total;
            }
            if (amount[0] == 0 && amount[1] == 0 && amount[2] != 0) {
                total += amount[2];
                return total;
            }
            amount[1]--;
            amount[2]--;
            total++;
        }
    }

}

class SmallestInfiniteSet {
    Set<Integer> removed;
    public SmallestInfiniteSet() {
            //初始化
            removed = new HashSet<>();
    }
    
    public int popSmallest() {
        //移除 并返回该无限集中的最小整数
        int n = 1;
        while (removed.contains(n)) {
            n++;
        }
        removed.add(n);
        return n;
    }
    
    public void addBack(int num) {
        //如果正整数 num 不 存在于无限集中，则将一个 num 添加 到该无限集中
       removed.remove(num);
    }
}
