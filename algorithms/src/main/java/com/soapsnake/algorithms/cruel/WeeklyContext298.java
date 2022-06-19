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
        //思路: 如果num是两位数,先把所有满足条件的两位数搞出来,然后用2sum?
        return 0;
    }


    public int longestSubsequence(String s, int k) {
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

    
}
