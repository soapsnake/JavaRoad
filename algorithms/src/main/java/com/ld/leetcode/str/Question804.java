package com.ld.leetcode.str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 */
class Question804 {

    public static void main(String[] args) {
        Question804 question804 = new Question804();

        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println(question804.uniqueMorseRepresentations(words));
    }

    public int uniqueMorseRepresentations(String[] words) {

        String[] origin = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        Map<Character, String> map = new HashMap<>();

        char a = 'a';
        for (int i = 0; i < origin.length; i++) {
            map.put((char) (a + i), origin[i]);
        }

        List<String> res = new ArrayList<>();
        String body = "";
        for (String s : words) {
            for (char c : s.toCharArray()) {
                body += map.get(c);
            }
            if (!res.contains(body)) {
                res.add(body);
            }
            body = "";
        }
        return res.size();
    }

    public int uniqueMorseRepresentations2(String[] words) {
        String[] MORSE = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
                "....", "..", ".---", "-.-", ".-..", "--", "-.",
                "---", ".--.", "--.-", ".-.", "...", "-", "..-",
                "...-", ".--", "-..-", "-.--", "--.."};
        HashSet<String> h = new HashSet<String>();

        for (int i = 0; i < words.length; i++) {
            char arr[] = words[i].toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arr.length; j++) {
                sb.append(MORSE[(arr[j]) - 97]);
            }
            h.add(sb.toString());
        }
        return h.size();
    }
}
