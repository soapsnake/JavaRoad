package com.soapsnake.algorithms.weekly;

import java.util.*;
/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-05-15
 * JavaRoad
 */
public class WeeklyContext293 {




    public List<String> removeAnagrams(String[] words) {
        //读题: 从words中删除字母异位词
        //思路:问题是怎么高效查异位词
        //如果continue是异位才能判重,否则不能
        Set<String> set = new HashSet<>();
        List<String> res = new ArrayList<>();
        for (String word : words) {
            char[] tmp = word.toCharArray();
            Arrays.sort(tmp);
            if (!set.contains(String.valueOf(tmp))) {
                res.add(word);
                set.clear();
                set.add(String.valueOf(tmp));
            }
        }
        return res;
    }

    public int maxConsecutive(int bottom, int top, int[] special) {
        //读题 i ->

        //思路: 新数组,最小值取 min(bottom, special[0]), 最大值取 max(top, special[n- 1]);
       //bottom -> special左边第一个大于bottom的值, speical 第一个小于top的值
        Arrays.sort(special);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < special.length; i++) {
            map.put(special[i], i);
        }
        int startIndex = map.ceilingEntry(bottom).getValue();
        int endIndex = map.floorEntry(top).getValue();
        System.out.println("startIndex = " + startIndex + " endIndex = " + endIndex);
        int max = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            if (i == startIndex) {
                max = Math.max(max, special[i] - bottom);
            }
            if (i - 1 >= 0) {
                max = Math.max(max, special[i] - special[i - 1] - 1);
            }
            if (i == endIndex) {
                max = Math.max(max, top - special[i]);
                }
            }
        return max;
    }

    public int largestCombination(int[] candidates) {
        int res = candidates[0];
        int ans = 1;
        for (int i = 1; i < candidates.length; i++) {
            if ((res & candidates[i]) > 0) {
                res = res & candidates[i];
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        WeeklyContext293 weeklyContext293 = new WeeklyContext293();
        int[] sp = {16,17,71,62,12,24,14};   //should 12
        //
        //
        //System.out.println("1000 & 1 = " + (1000 & 1));
        //
        //System.out.println("1001 & 1 = " + (1001 & 1));
        //
        //System.out.println("1011 & 1 = " + (1011 & 1));
        //
        //System.out.println("1110 & 1 = " + (1110 & 1));
        //
        System.out.println(weeklyContext293.largestCombination2(sp));

        //System.out.println(weeklyContext293.showBinary(17));
    }

    public String showBinary(int n) {
        System.out.println("haha = " + Integer.toBinaryString(n));
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.insert(0,n & 1);
            n >>= 1;
        }
        return sb.toString();
    }

    public int largestCombination2(int[] candidates) {
        int[] cnt =  new int[32];
        for (int candidate : candidates) {
            int i = 0;
            while (candidate > 0) {
                cnt[i++] += candidate & 1;
                candidate >>= 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = Math.max(res, cnt[i]);
        }
        return res;
    }
}


class CountIntervals {
    TreeMap<Integer, Integer> map;
    int sum;
    public CountIntervals() {
        map = new TreeMap<>();
        sum = 0;
    }

    public void add(int left, int right) {
        Integer oldLeft = map.floorKey(right);
        int newLeft = left;
        int newRight = right;
        while (oldLeft != null && map.get(oldLeft) >= newLeft) {
            Integer oldRight = map.get(oldLeft);
            newLeft = Math.min(newLeft, oldLeft);
            newRight = Math.max(newRight, oldRight);
            sum -= oldRight - oldLeft + 1;
            map.remove(oldLeft);
            oldLeft = map.floorKey(newRight);
        }
        sum += newRight - newLeft + 1;
        map.put(newLeft, newRight);
    }

    public int count() {
        return sum;
    }
}