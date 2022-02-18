package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.swing.text.AbstractDocument.Content;

import org.junit.Test;

/**
 * @author soapsnake <soapsnake@gmail.com>
 *         Created on 2022-02-10
 *         JavaRoad
 */
public class AboutBinary {

    /**
     * 二进制常用的运算有:
     * 1. 整数n的二进制表示中第k位(从右往左)是0还是1: n >> k & 1 (就是把n二进制的k位右移k位然后&1)
     * 2. lowbit(x), 返回x的最后一位1 : x & -x
     * //这个可以用来求整数n的二进制表示法中1的个数,类似于Integer.bitCount() ????
     * 3. 原码, 反码, 补码
     */

    public static void main(String[] args) {
        // List<String> list = Arrays.asList("beijing", "beijing", "shanghai",
        // "shenzhen");
        // Set<String> set = new HashSet<>(list);
        // System.out.println(set);

        AboutBinary aboutBinary = new AboutBinary();
        int[] nums1 = { 1, 2 };
        int[] nums2 = { 2, 3 };
        System.out.println(aboutBinary.minimumXORSum(nums1, nums2));
    }

    // 1879,这道题根本就不会做,基本思路是动态规划
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int max = 1 << nums1.length;
        int ans[] = new int[max];// ans[i]表示的是利用nums1的0——bitcount(i)-1，并且利用了nums2的相应位置的最小值
        Arrays.fill(ans, 1 << 30);
        ans[0] = 0;
        for (int i = 1; i < max; i++) {
            int bitNum = Integer.bitCount(i);// 此时的状态具有bitNum个比特，需要通过bitNum-1个比特的状态转移而得
            for (int j = 0; j < nums1.length; j++) {
                int pre = i ^ (1 << j);// 比i少一个bit的可能的组合
                if (pre < i) {
                    ans[i] = Math.min(ans[i], ans[pre] + (nums1[bitNum - 1] ^ nums2[j]));
                }
            }
        }
        return ans[max - 1];
    }

    public int minimumXORSum3(int[] nums1, int[] nums2) {
        int max = 1 << nums1.length;
        int ans[] = new int[max];
        Arrays.fill(ans, 1 << 30);
        ans[0] = 0;
        for (int i = 1; i < max; i++) {
            int bitNum = Integer.bitCount(i);
            for (int j = 0; j < nums1.length; j++) {
                int pre  = i ^ (1 << j);
                if (pre < i) {
                    ans[i] = Math.min(ans[i], ans[pre] + (nums1[bitNum - 1] ^ nums2[j]));
                }
            }
        }
        return ans[max - 1];
    }



    // 1879
    public int minimumXORSum2(int[] nums1, int[] nums2) {
        // 重新排列
        // 求异或的和
        int res = Integer.MAX_VALUE;
        List<int[]> list1 = this.reArrange(nums1);
        List<int[]> list2 = this.reArrange(nums2);

        // list1.forEach(o -> System.out.println(Arrays.toString(o)));
        // System.out.println("end");
        // list2.forEach(o -> System.out.println(Arrays.toString(o)));

        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                res = Math.min(res, sum(list1.get(i), list2.get(j)));
            }
        }
        return res;
    }

    private int sum(int[] is, int[] is2) {
        int res = 0;
        for (int i = 0; i < is2.length; i++) {
            res += is[i] ^ is2[i];
        }
        return res;
    }

    private List<int[]> reArrange(int[] nums1) {
        List<int[]> res = new ArrayList<>();
        helper2(nums1, 0, new ArrayList<>(), res, new boolean[nums1.length]);
        return res;
    }

    private void helper2(int[] nums1, int i, List<Integer> temp, List<int[]> res, boolean[] used) {
        if (temp.size() == nums1.length) {
            int[] ints = list2array(temp);
            if (!res.contains(ints)) {
                res.add(ints);
                return;
            }
        }
        for (int j = 0; j < nums1.length; j++) {
            if (used[j]) {
                continue;
            }
            used[j] = true;
            temp.add(nums1[j]);
            helper2(nums1, j + 1, temp, res, used);
            temp.remove(temp.size() - 1);
            used[j] = false;
        }
    }

    private int[] list2array(List<Integer> temp) {
        int[] res = new int[temp.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = temp.get(i);
        }
        return res;
    }

    List<Integer> list = new ArrayList<>();
    List<Integer> list2= new ArrayList<>();
    List<Integer> list3 = new ArrayList<>();
    List<Integer> list4 = new ArrayList<>();
    List<Integer> list5 = new ArrayList<>();
    List<Integer> list6 = new ArrayList<>();
    List<Integer> list7 = new ArrayList<>();
    List<Integer> list8 = new ArrayList<>();
    List<Integer> list9 = new ArrayList<>();


    List<Integer> list10 = new ArrayList<>();

    List<Integer> list11 = new ArrayList<>();
    List<Integer> list12 = new ArrayList<>();
    List<Integer> list13 = new ArrayList<>();
    List<Integer> list14 = new ArrayList<>();
    List<Integer> list15 = new ArrayList<>();

    

}
