package com.soapsnake.algorithms.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-03-01
 */
public class Question895 {

    class FreqStack {
        Map<Integer, Integer> freq;   //x -> x的出现次数
        Map<Integer, Stack<Integer>> m;   //出现次数 -> 出现该次数的所有元素的栈,
        int maxfreq;

        public FreqStack() {
            this.freq = new HashMap<>();
            this.m = new HashMap<>();
            this.maxfreq = 0;
        }

        public void push(int x) {
            int f = freq.getOrDefault(x, 0) + 1;
            freq.put(x, f);
            maxfreq = Math.max(maxfreq, f);
            if (!m.containsKey(f)) {
                m.put(f, new Stack<>());
            }
            m.get(f).add(x);
        }

        public int pop() {
            int x = m.get(maxfreq).pop();  //从出现次数最多的元素栈里弹出一个数字
            freq.put(x, maxfreq - 1);  //减小x的频率
            if (m.get(maxfreq).size() == 0) maxfreq--;
            return x;
        }
    }
}
