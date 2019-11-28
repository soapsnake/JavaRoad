package com.soapsnake.algorithms.leetcode.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-04 20:40
 */
public class Question93 {

    public static void main(String[] args) {
        Question93 question93 = new Question93();
        String s = "25525511135";
        System.out.println(question93.restoreIpAddresses(s));
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> total = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return new ArrayList<>();
        }
        //s >= 4 && <= 12
        //每一个数字应该是0 ~ 255
        //思路,可以先暴力罗列所有可能的字符串组,然后进行校验,暴力罗列可以使用树的深度递归

        dfsTree(s, total, 0, "", 0);
        return total;
    }

    private void dfsTree(String ip, List<String> solutions, int idx, String restored, int count) {
        if (count > 4) {
            return;
        }
        if (count == 4 && idx == ip.length()) {
            solutions.add(restored);
        }

        for (int i = 1; i < 4; i++) {
            if (idx + i > ip.length()) {
                break;
            }
            String s = ip.substring(idx, idx + i);
            if ((s.startsWith("0") && s.length() > 1) || (i == 3 && Integer.parseInt(s) >= 256)) {
                continue;
            }
            dfsTree(ip, solutions, idx + i, restored + s + (count == 3 ? "" : "."), count + 1);
        }
    }
}
