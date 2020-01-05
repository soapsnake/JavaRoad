package com.soapsnake.algorithms.leetcode.binarytree;

public class Question331 {


    /**
     * Example 1:
     * Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
     * Output: true
     * <p>
     * Example 2:
     * Input: "1,#"
     * Output: false
     * <p>
     * 判定一个字符串是不是一个二叉树的先序便利结果(先根遍历)
     */
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for (String node: nodes) {
            if (--diff < 0) return false;
            if (!node.equals("#")) diff += 2;
        }
        return diff == 0;
    }

}