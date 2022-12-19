package com.soapsnake.algorithms.weekly;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-10-02
 * JavaRoad
 */
public class WeeklyContext316 {

    public static void main(String[] args) {
        WeeklyContext316 c = new WeeklyContext316();
        //System.out.println(c);
        //
        //System.out.println(Integer.toBinaryString(9));
        //System.out.println(Integer.toBinaryString(81));
        //
        //System.out.println(Integer.toBinaryString(13));
        ////System.out.println(Integer.toBinaryString(11));
        //
        //String s = "2102fe0616653111529483837ebfe8";
        //System.out.println(s.charAt(23));
        //System.out.println(s.charAt(24));
        //System.out.println(s.charAt(25));


        System.out.println(Integer.parseInt("02", 10));

    }


    public boolean haveConflict(String[] event1, String[] event2) {
        if (compare(event1[0], event2[0]) < 0) {
            return compare(event1[1], event2[0]) < 0;
        } else if (compare(event1[0], event2[0]) > 0) {
            return compare(event1[1], event2[0]) < 0;
        } else {
            return false;
        }
    }

    private int compare(String s, String s1) {
        String[] time1 = s.split(":");
        String[] time2 = s1.split(":");
        if (Integer.parseInt(time1[0], 10) < Integer.parseInt(time2[0], 10)) {
            return -1;
        } else if (Integer.parseInt(time1[0], 10) > Integer.parseInt(time2[0], 10)) {
            return 1;
        } else {
            if (Integer.parseInt(time1[1], 10) < Integer.parseInt(time2[1], 10)) {
                return -1;
            } else if (Integer.parseInt(time1[1], 10) > Integer.parseInt(time2[1], 10)) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public int subarrayGCD(int[] nums, int k) {
        //问题1: 如何快速判断一个数组的最大公约数是不是k
        //问题2:
        return 0;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public long minCost(int[] nums, int[] cost) {
        return 0;
    }
}
