package com.soapsnake.algorithms.cruel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-05-01
 * JavaRoad
 */
public class WeeklyContext291 {


    public int minimumCardPickup2(int[] cards) {
        //读题: 从cards里面取一个连续子串,使得该子串中包含有两个相同number, 要求这个连续子串最小,感觉是滑动窗口???
        //思路: 双指针
        int n = cards.length;
        int res = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();  // number -> index
        for (int i = 0; i < n; i++) {
            Integer preIndex = map.get(cards[i]);
            if (preIndex == null) {
                map.put(cards[i], i);
            } else {
                int range = i - preIndex + 1;
                res = Math.min(res, range);
                map.put(cards[i], i);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    public String removeDigit(String number, char digit) {
        //读题: 从number中移除digit,可能多于一个,要求移除后的结果最大.
        //思路: 只能暴力试了吧
        BigDecimal b = new BigDecimal(0);
        StringBuilder sb;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == digit) {
                sb = new StringBuilder(number);
                sb.replace(i, i + 1, "");
                BigDecimal cur = new BigDecimal(sb.toString());
                if (cur.compareTo(b) > 0) {
                    b = cur;
                }
            }
        }
        return b.toString();
    }

    public static void main(String[] args) {
        WeeklyContext291 weeklyContext291 = new WeeklyContext291();
        //
        //System.out.println();
        //String number = "123";
        //char c = '3';
        //System.out.println(weeklyContext291.removeDigit(number, c));


        //int[] numer  = {1,0,5,3};
        int[] numer = {2,3,3,2,2};
        System.out.println(weeklyContext291.countDistinct(numer, 2, 2));  //3
    }

    public int countDistinct(int[] nums, int k, int p) {
        //读题, nums有多少个连续子数组,这些子数组的特点是最多有k个元素可以被p整除,如果k=3,那数组长度可以是5,但是其中有3个数可以被p整除?
        //难点在:如何构造子数组,
        //
        int res = 0;
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int tmp = 0; //可整除数
            for (int j = i; j < nums.length; j++) {
                if (nums[j] % p != 0) {

                }else {
                    tmp++;
                }
                if (tmp <= k) {
                        //System.out.println("i = " + i + " j = " + j);
                        List<Integer> list = new ArrayList<>();
                        for (int m = i; m <= j; m++) {
                            list.add(nums[m]);
                        }
                        set.add(list);
                        res++;
                    } else {
                        break;
                    }
            }
        }
        return set.size();
    }

    public long appealSum(String s) {
        //思路:依次构造n长度子串,算每个子串的值,然后累加
        long res = 0;
        for (int i = 0; i < s.length(); i++) {

            for (int j = 0; j < s.length(); j++) {

            }
        }
        return 0;
    }

}
