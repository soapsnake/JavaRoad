package com.soapsnake.algorithms.cruel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class WeeklyContext298 {
    
    public String greatestLetter(String s) {
        //读题: 1. 结果最多一个,要大写. 
        //字符必须大小写都出现过, 
        //如果有多个都满足,返回字母表序列最前的
        int[] small = new int[26];
        int[] big = new int[26];
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                small[c - 'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                big[c - 'A']++;
            }
        }
        List<Character> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (small[i] > 0 && big[i] > 0) {
                res.add((char) ('A' + i));
            }
        }
        if (res.size() == 0) return "";
        Collections.sort(res);
        return res.get(res.size() - 1) + "";
    }

    public int minimumNumbers(int num, int k) {
        //1.求得是一个list的最小size, list中每个数字各位数都要是k, list所有数字相加得num
        //思路: 先把1 -> num所有尾数是k的数字全部枚举出来,然后使用coin change(同一枚硬币有无数枚)
        int[] tmp;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            if (i % 10 == k) {
                list.add(i);
            }
        }
        tmp = list.stream().mapToInt(x -> x).toArray();
        return coinChange(tmp, num);
    }

    private int coinChange(int[] coins, int amount) {
        //dp[i] = 金额达到i需要的最少coin数量
        //dp[i] = dp[i - coins[k]] + 1;
        //dp[i] = i;
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i;
        }
        for (int i = 0; i < coins.length; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = dp[i - coin] + 1;
                }
            }
        }
        return dp[amount];
    }

    public int longestSubsequence(String s, int k) {
        int zeros = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeros++;
            }
        }
        int ones = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                if (Long.parseLong(s.substring(i), 2) > k) {
                    break;
                } 
                ones++;
            }
        }
        return zeros + ones;
    }



    public int longestSubsequence2(String s, int k) {
        //dp[i] 到索引i时的最长串的长度 dp[n]  new int[n + 1] 
        //dp[i] -> max(dp[i - 1] + 1, dp[i - 1])
        //dp[0] = 1,  
        String prev = "";
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {

            //每一个都有取和不取两个状态;
            
            if (check(prev + s.charAt(i - 1), k)) {
                dp[i] = dp[i - 1] + 1; //
                prev += s.charAt(i - 1);
                System.out.println(prev);
            } else {
                dp[i] = dp[i - 1];
            }
        }
        System.out.println(Arrays.toString(dp));
        return prev.length();
    }

    private boolean check(String string, int k) {
        boolean res = Integer.parseInt(string, 2) <= k;
        System.out.println("s = " + string + "  => res = " + res);
        return  res;
    }

    public static void main(String[] args) throws Exception {
        WeeklyContext298 week = new WeeklyContext298();
        String s = "00101001";
        int k = 1;
        System.out.println(week.longestSubsequence(s, k));
    }


    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            count = 0;
            dfs(i, new boolean[n], bombs);
            ans = Math.max(ans, count);
        }
        return ans;
    }
    int count = 0;
    private void dfs(int idx, boolean[] v, int[][] bombs) {
        count++;
        v[idx] = true;
        int n = bombs.length;
        for (int i = 0; i < n; i++) {
            if (!v[i] && inRange(bombs[idx], bombs[i])) {
                v[i] = true;
                dfs(i, v, bombs);
            }
        }
    }

    private boolean inRange(int[] a, int[] b) {
        long dx = a[0] - b[0], dy = a[1] - b[1], r = a[2];
        return dx * dx + dy * dy <= r * r;
    }

    
}
