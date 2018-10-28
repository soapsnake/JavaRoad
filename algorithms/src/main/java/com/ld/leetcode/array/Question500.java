package com.ld.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author soapsnake
 * @date 2018/10/28
 *
 * Given a List of words, return the words that can be typed using letters of alphabet
 * on only one row's of American keyboard like the image below.
 */
public class Question500 {

    public String[] findWords(String[] words) {
        List<String> standers = new ArrayList<>();
        standers.add("QWERTYUIOP");
        standers.add("ASDFGHJKL");
        standers.add("ZXCVBNM");

        List<String> res = new ArrayList<>();
        for (String s : words) {
            String stand = "";
            int count = 0;
            for (char c : s.toCharArray()) {
                if (stand.equals("")) {
                    for (String entry : standers) {
                        if (entry.contains((c + "").toUpperCase())) {
                            stand = entry;
                            break;
                        }
                    }
                }

                if (stand.contains((c + "").toUpperCase())) {
                    count++;
                }else {
                    break;
                }
            }
            if (count == s.length()) {
                res.add(s);
            }
        }
//        String[] stringres = new String[res.size()];
//        int i = 0;
//        for (String s: res) {
//            stringres[i++] = s;
//        }
        return res.toArray(new String[0]);  //字符串list转数组的最有效方法
    }

    public static void main(String[] args) {
        Question500 question500 = new Question500();
        String[] words = {"Hello", "Alaska", "Dad", "Peace"};

        System.out.println(Arrays.toString(question500.findWords(words)));
    }
}
