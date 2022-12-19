package com.soapsnake.algorithms.weekly;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author soapsnake <soapsnake@gmail.com>
 * Created on 2022-04-10
 * JavaRoad
 */
public class WeeklyContext288 {

    public static void main(String[] args) throws Exception {
        WeeklyContext288 w = new WeeklyContext288();
        String num = "247+38";
        System.out.println(w.minimizeResult(num));
    }

    public String minimizeResult(String expression) {
        //按加号拆两版
        //第一版,维护指针i, 乘法部分1 + 数字部分1
        //后半部分,维护指针就, 数字部分2 + 乘法部分2
        //res = 乘法部分1 * (数字1 + 数字2) * 乘法部分2
        long total = Integer.MAX_VALUE;

        String[] tmp = expression.split("\\+");
        int n = tmp[0].length();
        int m = tmp[1].length();
        String str1 = tmp[0];
        String str2 = tmp[1];
        int l = 0, r = 0;
        for (int i = n - 1; i >= 0; i--) {
            //最左部分, 可为0
            long cheng1 = str1.substring(0, i).equals("") ? 1L : Long.parseLong(str1.substring(0, i));
            //+号左部,必须有值
            long shu1 = Long.parseLong(str1.substring(i));
            for (int j = 1; j <= m; j++) {
                long shu2 = Long.parseLong(str2.substring(0, j));
                long cheng2 = str2.substring(j).equals("") ? 1L : Long.parseLong(str2.substring(j));
                long linshi = cheng1 * (shu1 + shu2) * cheng2;
                if (linshi < total) {
                    total = linshi;
                    l = i;
                    r = j;
                }
            }
        }
        //System.out.println("total = " + total);
        StringBuilder sb = new StringBuilder(expression);
        sb.insert(l, "(");
        sb.insert(expression.indexOf("+") + r + 2, ")");
        return sb.toString();
    }

    public int largestInteger(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                int curi = chars[i] - '0';
                int curj = chars[j] - '0';
                if (curi % 2 == 0 && curj % 2 == 0) {
                    if (curi < curj) {
                        char tmp = chars[i];
                        chars[i] = chars[j];
                        chars[j] = tmp;
                    }
                } else if (curi % 2 != 0 && curj % 2 != 0) {
                    if (curi < curj) {
                        char tmp = chars[i];
                        chars[i] = chars[j];
                        chars[j] = tmp;
                    }
                }
            }
        }
        return Integer.parseInt(new String(chars));
    }

    //mod
    long mod = (long)(Math.pow(10,11) + 3);
    long p = 100003;
    long[] hash;
    public int longestCommonSubpath(int n, int[][] paths) {
        int min = 1;
        int max = Integer.MAX_VALUE;
        for (int[] path : paths) {
            max = Math.min(max, path.length);
        }
        hash = new long[max + 1];
        hash[0] = 1;
        for (int i = 1; i <= max; i++) {
            hash[i] = mulmod(hash[i - 1], p, mod);
        }
        int ans = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (solve(mid, paths)) {
                ans = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return ans;
    }

    public boolean solve(int len, int[][] paths) {
        Map<Long, Long> map = new HashMap<>();
        for (int[] path : paths) {
            long hash = 0L;
            Set<Long> set = new HashSet<>();
            for (int i = 0; i < len; i++) {
                hash = (hash + mulmod(this.hash[len - i], (path[i] + 1), mod)) % mod;
            }
            set.add(hash);
            for (int i = len; i < path.length; i++) {
                long new_hash = (hash - mulmod(this.hash[len], path[i - len] + 1, mod) + mod) % mod;
                new_hash = mulmod(new_hash, p, mod);
                new_hash = (new_hash + mulmod(this.hash[1], path[i] + 1, mod)) % mod;
                hash = new_hash;
                set.add(new_hash);
            }
            for (long hsh : set) {
                map.put(hsh, map.getOrDefault(hsh, 0L) + 1L);
                if (map.get(hsh) == paths.length) {
                    return true;
                }
            }
        }
        return false;
    }

    public long pow(long a, long b, int mod) {
        long val = a;
        long ans = 1L;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = mulmod(ans, val, mod);
            }
            val = mulmod(val, val, mod);
            b = b >> 1;
        }
        return ans % mod;
    }

    long mulmod(long a, long b, long mod) {
        return ((a % mod) * (b % mod)) % mod;
    }

}
