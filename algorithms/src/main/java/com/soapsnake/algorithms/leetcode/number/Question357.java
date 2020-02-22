package com.soapsnake.algorithms.leetcode.number;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-02-21
 */
public class Question357 {

    /**
     * Example:
     * Input: 2
     * Output: 91
     * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
     *              excluding 11,22,33,44,55,66,77,88,99
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0)
            return 1;
        int res = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n-- > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
        }
        return res;
    }
}
