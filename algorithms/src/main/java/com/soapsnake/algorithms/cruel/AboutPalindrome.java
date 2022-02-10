package com.soapsnake.algorithms.cruel;

import java.util.HashMap;
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
        return res;
    }
}
