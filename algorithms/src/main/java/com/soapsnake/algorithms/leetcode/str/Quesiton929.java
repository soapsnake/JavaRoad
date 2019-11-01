package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soapsnake
 * @date 2018/10/30
 * <p>
 * Example 1:
 * <p>
 * Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * Output: 2
 * Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 */
class Quesiton929 {

    public static void main(String[] args) {
        String[] emiai = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"
        };
        Quesiton929 quesiton929 = new Quesiton929();
        System.out.println(quesiton929.numUniqueEmails(emiai));
    }

    /**
     * 1. 先遇+截断
     * 2. 然后去掉剩余字串中的.
     *
     * @param emails
     * @return
     */
    public int numUniqueEmails(String[] emails) {
        List<String> res = new ArrayList<>();
        for (String s : emails) {
            String s1 = s.substring(0, s.indexOf('@'));
            String s2 = s.substring(s.indexOf('@') + 1);
            if (s1.contains("+")) {
                s1 = s1.substring(0, s1.indexOf('+'));
            }
            if (s1.contains(".")) {
                s1 = s1.replace(".", "");
            }
            if (!res.contains(s1 + s2)) {
                res.add(s1 + s2);
            }
        }
        return res.size();
    }
}
