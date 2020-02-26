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
    public int characterReplacement(String s, int k) {
        //含义,对s中的字符进行k次变换,求变换后s中能达到的重复字符的最大个数是多少
        //思路1:统计每个字符的出现次数,降序排列,依次按k尝试修改2->最后一次字符(实际累加这些出现数字)即可

        return 0;
    }
}
