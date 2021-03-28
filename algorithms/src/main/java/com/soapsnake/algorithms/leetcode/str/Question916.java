package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-03-27
 */
public class Question916 {

    /**
     * 整体思路,其实是看A中的字符串中的字符出现次数是否大于B中单词的对应字符出现次数
     * 如果比B大就可以判定该单词是满足的,否则就不行
     */
    //leetcode916
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] count = new int[26];
        int[] tmp;
        for (String word : B) {
            tmp = counter(word);
            for (int i = 0; i < count.length; i++) {
                count[i] = Math.max(count[i], tmp[i]);
            }
        }
        List<String> res = new ArrayList<>();
        int i;
        for (String a : A) {
            tmp = counter(a);
            for (i = 0; i < 26; ++i) {
                if (tmp[i] < count[i]) {
                    break;
                }
            }
            if (i == 26) {
                res.add(a);
            }
        }
        return res;
    }

    private int[] counter(String word) {
        int[] res = new int[26];
        for (int i = 0; i < word.length(); i++) {
            res[word.charAt(i) - 'a']++;
        }
        return res;
    }
}
