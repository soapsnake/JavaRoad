package com.soapsnake.algorithms.leetcode.dp;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-03-12
 */
public class Question467 {

    /**
     * Example 2:
     * Input: "cac"
     * Output: 2
     * Explanation: There are two substrings "a", "c" of string "cac" in the string s.
     *
     *
     * Example 3:
     * Input: "zab"
     * Output: 6
     * Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
     */
    //leetcode467
    public int findSubstringInWraproundString(String p) {
        // count[i] is the maximum unique substring end with ith letter.
        // 0 - 'a', 1 - 'b', ..., 25 - 'z'.
        int[] count = new int[26];

        // store longest contiguous substring ends at current position.
        int maxLengthCur = 0;

        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25))) {
                maxLengthCur++;
            }
            else {
                maxLengthCur = 1;
            }

            int index = p.charAt(i) - 'a';
            count[index] = Math.max(count[index], maxLengthCur);
        }

        // Sum to get result
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += count[i];
        }
        return sum;
    }


}
