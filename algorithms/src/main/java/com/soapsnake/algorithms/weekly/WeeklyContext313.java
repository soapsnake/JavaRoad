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
public class WeeklyContext313 {

    public static void main(String[] args) {
        WeeklyContext313 c = new WeeklyContext313();
        //System.out.println(c);
        //
        //System.out.println(Integer.toBinaryString(9));
        //System.out.println(Integer.toBinaryString(81));
        //
        //System.out.println(Integer.toBinaryString(13));
        //System.out.println(Integer.toBinaryString(11));

        String s = "2102fe0616653111529483837ebfe8";
        System.out.println(s.charAt(23));
        System.out.println(s.charAt(24));
        System.out.println(s.charAt(25));


    }

    public int commonFactors(int a, int b) {
        List<Integer> list = compute(a);
        int res =   0;
        for (int t : list) {
            if (b % t == 0) {
                res++;
            }
        }
        return res;
    }

    public static List<Integer> compute(int m) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= m / i; i++) {
            if (m % i == 0) {
                set.add(i);
                set.add(m / i);
            }
        }
        set.add(m);
        List<Integer> list = new ArrayList<>(set);
        return list;
    }


    public int maxSum(int[][] grid) {
         m = grid.length;
         n = grid[0].length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 2 >= m || j - 1 < 0 || j + 1 >= n) {
                    continue;
                }
                res = Math.max(res, compute2(grid, i, j));
            }
        }
        return res;
    }
    int m = 0;
    int n = 0;
    private int compute2(int[][] grid, int i, int j) {
        int res = grid[i][j - 1] + grid[i][j] + grid[i][j + 1];
        res += grid[i + 1][j];
        res += grid[i + 2][j - 1] + grid[i + 2][j] + grid[i + 2][j + 1];
        return res;
    }


    public int minimizeXor(int num1, int num2) {
        //本质上求,对nums2的二进制字符串进行排列,然后重排列后的字符串要和num1的二进制串尽可能一致
        String s1 = Integer.toBinaryString(num1);
        String s2 = Integer.toBinaryString(num2);
        System.out.println(s1);
        int s2count1 = 0;
        int s2count0 = 0;

        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == '0') {
                s2count0++;
            } else {
                s2count1++;
            }
        }
        StringBuilder sb = new StringBuilder();
        System.out.println(s1);
        for (int i = 0 ; i < s1.length(); i++ ) {
            if (s1.charAt(i) == '0') {
                if (s2count0 > 0) {
                    s2count0--;
                    sb.append(0);
                } else {
                    sb.append(1);
                }
            } else {
                if (s2count1 > 0) {
                    s2count1--;
                    sb.append(1);
                } else {
                    sb.append(0);
                }
            }
        }
        //如果s1很短,s2用了某些字符就拼完了,这时候s2还剩余字符
        for (int i = sb.length() - 1; i >= 0; i--) {
                if (s2count1 > 0 && sb.charAt(i) == '0') {
                    sb.replace(i,i, "1");
                    s2count1--;
                }
            }
        while (s2count1 > 0) {
            sb.append(1);
            s2count1--;
        }
        System.out.println(sb);
        return Integer.parseInt(sb.toString(), 2);
    }


    public int deleteString(String s) {
        //思路: 从前往后遍历,记录每个位置之前的
        return 0;
    }
}
