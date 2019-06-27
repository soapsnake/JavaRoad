package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.List;

public class Question299 {

    /**
     * Input: secret = "1807", guess = "7810"
     * Output: "1A3B"
     * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        //A表示guss里面处于正确位置的字符数, B表示guess里处于错误位置的字符数
        int countA = 0;
        int countB = 0;
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < secret.length(); i++) {
            list.add(secret.charAt(i));
        }
        List<Character> bulls = new ArrayList<>();
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            char source = secret.charAt(i);
            if (c == source) {
                //字符位置一样
                countA++;
                list.remove(Character.valueOf(c));
            } else {
                if (list.contains(c)) {
                    bulls.add(c);
                }
            }
        }
        for (char c : bulls) {
            if (list.contains(c))
                countB++;
                list.remove(Character.valueOf(c));
        }
        return countA +  "A" + countB + "B";
    }


    public String getHint2(String secret, String guess) {
        int len = secret.length();
        int[] secretarr = new int[10];
        int[] guessarr = new int[10];
        int bull = 0, cow = 0;
        for (int i = 0; i < len; ++i) {
            if (secret.charAt(i) == guess.charAt(i)) {
                ++bull;
            } else {
                ++secretarr[secret.charAt(i) - '0'];
                ++guessarr[guess.charAt(i) - '0'];
            }
        }
        for (int i = 0; i < 10; ++i) {
            cow += Math.min(secretarr[i], guessarr[i]);
        }
        return "" + bull + "A" + cow + "B";
    }

    public static void main(String[] args) {
        Question299 question299 = new Question299();
        String secret = "1123";
        String guess =  "0111";
        System.out.println(question299.getHint(secret, guess));  //should 1A1B
    }
}
