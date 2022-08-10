package com.soapsnake.algorithms.cruel;

import lombok.var;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *xxxx
 * Created on 2022-01-31
 */
public class AboutPalindrome {

    //2131
    public int longestPalindrome(String[] words) {
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder(words[i]);
            String reverse = sb.reverse().toString();
            if (map.getOrDefault(reverse, 0) > 0) {
                res += 4;
                map.put(reverse, map.get(reverse) - 1);
            } else {
                map.put(words[i], map.getOrDefault(words[i], 0) + 1);
            }
        }
        for (String word : map.keySet()) {
            if (map.getOrDefault(word, 0) > 0 && word.charAt(0) == word.charAt(1)) {
                res += 2;
                break;
            }
        }

        int[] temp = new int[res];
        System.out.println(Arrays.toString(temp));
        return res;
    }

    static final int MOD = (int) 1e9 + 7, MAX = (int) 1e4 + 1, MX_K = 13;
    static List[] ks = new List[MAX];
    static int[][] c = new int[MAX + MX_K][MX_K + 1];

    static {
        for (var i = 1; i < MAX; i++) {
            ks[i] = new ArrayList<Integer>();
            var x = i;
            for (var p = 2; p * p <= x; ++p) {
                if (x % p == 0) {
                    var k = 1;
                    for (x /= p; x % p == 0; x /= p) ++k;
                    ks[i].add(k);
                }
            }
            if (x > 1) ks[i].add(1);
        }

        c[0][0] = 1;
        for (var i = 1; i < MAX + MX_K; ++i) {
            c[i][0] = 1;
            for (var j = 1; j <= Math.min(i, MX_K); ++j)
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
        }
    }

    public int idealArrays(int n, int maxValue) {
        var ans = 0L;
        for (var x = 1; x <= maxValue; ++x) {
            var mul = 1L;
            for (var k : ks[x]) mul = mul * c[n + (int) k - 1][(int) k] % MOD;
            ans += mul;
        }
        return (int) (ans % MOD);
    }
}
