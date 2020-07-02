package com.soapsnake.algorithms.leetcode.str;

import java.rmi.StubNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-27
 */
public class Question648 {

    //leetcode648
    public String replaceWords(List<String> dict, String sentence) {
        if (dict == null || dict.size() == 0) return sentence;
        Set<String> set = new HashSet<>(dict);
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split("\\s+");

        for (String word : words) {
            String prefix = "";
            for (int i = 1; i <= word.length(); i++) {
                prefix = word.substring(0, i);
                if (set.contains(prefix)) break;
            }
            sb.append(" ").append(prefix);
        }
        return sb.deleteCharAt(0).toString();
    }

    @Test
    public void test() {
        List<String> dict = Arrays.asList(new String[] {"a","b","c"});
        String sents = "aadsfasf absbs bbab cadsfafs";
        System.out.println(replaceWords(dict, sents));
    }
}
