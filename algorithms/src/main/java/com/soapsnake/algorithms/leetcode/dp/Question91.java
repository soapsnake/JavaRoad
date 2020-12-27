package com.soapsnake.algorithms.leetcode.dp;

public class Question91 {

    /**
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     *
     * Example 1:
     * Input: "12"
     * Output: 2
     * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     *
     *
     * Assigning memo[n] = 1; means when the string is empty, there is only one answer.
     * memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0; means when there is only one character in the string,
     * if this character is not 0, there will be an answer, or there will be no answer.
     * Then it starts the dp portion. When we add a letter from the end of the string,
     * if the first two letters <=26, we can get memo[n]=memo[n+1]+memo[n+2].
     * For example, the String now is "123xxxx" and we know all the result from 2  .
     * Because 12<26, we can make this string either"12"+"3xxxx" or 1+23xxxx which is exactly
     * memo[n]=memo[n-1]+memo[n-2].
     * if the String is"32xxxx" memo[n]=memo[n+1]. if there are 0s in the string,
     * we should skip it and look at the next character because there is no answer when the string begins with 0.
     */
    public int numDecodings(String s) {
        if (s == null) {
            return 0;
        }
        int n = s.length();
        if (n == 0) return 0;
        int[] memo = new int[n+1];
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0') {

            } else {
                memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];
            }
        return memo[0];
    }
}
