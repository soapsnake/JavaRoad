package com.soapsnake.algorithms.leetcode.str;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-02-26
 */
public class Question424 {

    /**
     * Example 2:
     *
     * Input:
     * s = "AABABBA", k = 1
     *
     * Output:
     * 4
     *
     * Explanation:
     * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
     * The substring "BBBB" has the longest repeating letters, which is 4.
     */
    //leetcode424
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int start = 0, maxCount = 0, maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Question424 question424 = new Question424();
        System.out.println(question424.characterReplacement("AABABBA", 0));
    }
}
