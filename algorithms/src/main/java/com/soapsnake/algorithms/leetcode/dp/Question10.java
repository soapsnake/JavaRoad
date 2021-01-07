package com.soapsnake.algorithms.leetcode.dp;

import com.soapsnake.algorithms.structures.list.ListNode;

/**
 *
 * Created on 2020-07-03
 */
public class Question10 {

    /**
     * 模式匹配,s是否是p的字串,p中的.可以代表任意字符,*表示重复前一个字符0次(删除前一个字符)或者重复1次
     * Example 4:
     * Input:
     * s = "aab"
     * p = "c*a*b"
     * Output: true
     * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
     */
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null)
            return false;

        int m = s.length();
        int n = p.length();

        // dp[i][j] represents if the 1st i characters in s can match the 1st j characters in p
        boolean[][] dp = new boolean[m + 1][n + 1];  //s中的前i个字符串能否匹配p中的j个字符串

        // initialization:
        // 1. dp[0][0] = true, since empty string matches empty pattern
        dp[0][0] = true;

        // 2. dp[i][0] = false(which is default value of the boolean array) since empty pattern cannot match non-empty string
        // 3. dp[0][j]: what pattern matches empty string ""? It should be #*#*#*#*..., or (#*)* if allow me to represent regex using regex :P,
        // and for this case we need to check manually:
        // as we can see, the length of pattern should be even && the character at the even position should be *,
        // thus for odd length, dp[0][j] = false which is default. So we can just skip the odd position, i.e. j starts from 2, the interval of j is also 2.
        // and notice that the length of repeat sub-pattern #* is only 2, we can just make use of dp[0][j - 2] rather than scanning j length each time
        // for checking if it matches #*#*#*#*.
        for (int j = 2; j < n + 1; j += 2) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }

        // Induction rule is very similar to edit distance, where we also consider from the end. And it is based on what character in the pattern we meet.
        // 1. if p.charAt(j) == s.charAt(i), dp[i][j] = dp[i - 1][j - 1]
        //    ######a(i)
        //    ####a(j)
        // 2. if p.charAt(j) == '.', dp[i][j] = dp[i - 1][j - 1]
        // 	  #######a(i)
        //    ####.(j)
        // 3. if p.charAt(j) == '*':
        //    1. if p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i), then b* is counted as empty. dp[i][j] = dp[i][j - 2]
        //       #####a(i)
        //       ####b*(j)
        //    2.if p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i):
        //       ######a(i)
        //       ####.*(j)
        //
        // 	  	 #####a(i)
        //    	 ###a*(j)
        //      2.1 if p.charAt(j - 1) is counted as empty, then dp[i][j] = dp[i][j - 2]
        //      2.2 if counted as one, then dp[i][j] = dp[i - 1][j - 2]
        //      2.3 if counted as multiple, then dp[i][j] = dp[i - 1][j]

        // recap:
        // dp[i][j] = dp[i - 1][j - 1]
        // dp[i][j] = dp[i - 1][j - 1]
        // dp[i][j] = dp[i][j - 2]
        // dp[i][j] = dp[i][j - 2]
        // dp[i][j] = dp[i - 1][j - 2]
        // dp[i][j] = dp[i - 1][j]
        // Observation: from above, we can see to get dp[i][j], we need to know previous elements in dp, i.e. we need to compute them first.
        // which determines i goes from 1 to m - 1, j goes from 1 to n + 1

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curS == curP || curP == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (curP == '*') {
                    char preCurP = p.charAt(j - 2);
                    if (preCurP != '.' && preCurP != curS) {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = (dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j]);
                    }
                }
            }
        }
        return dp[m][n];
    }

    //leetcode10
    public boolean isMatch2(String s, String p) {
        // corner case
        if (s == null || p == null)
            return false;

        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];  //s中的前i个字符串能否匹配p中的j个字符串
        dp[0][0] = true;
        for (int j = 2; j < n + 1; j += 2) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curS == curP || curP == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (curP == '*') {
                    char preCurP = p.charAt(j - 2);
                    if (preCurP != '.' && preCurP != curS) {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = (dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j]);
                    }
                }
            }
        }
        return dp[m][n];
    }


    public ListNode deleteDuplicates(ListNode head) {
        //删除重复节点,要求重复节点本身也要被删除
        if (head == null) {
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode pre = fakeHead;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.next.val == cur.val) {
                cur = cur.next;
            }

            //到此,cur节点和下一节点已经不相同
            if (pre.next == cur) {
                //pre的下一个节点没有变,还是cur
                pre = pre.next;
            } else {
                //pre的下一个节点变了,意思就是发现了重复,要删除节点
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return fakeHead.next;
    }









}
