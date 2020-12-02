package com.soapsnake.algorithms.alib;


import com.soapsnake.algorithms.structures.tree.TreeNode;

public class SomeTedyrtd {


    public int subString(String a, String b) {
        if (b == null || b == "") {
            return 0;
        }
        if (a == null || b.length() > a.length()) {
            return -1;
        }
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        int start = -1;
        for (int i = 0; i < aChars.length; i++) {
            for (int j = 0; j < bChars.length; ) {
                if (j == bChars.length - 1) {
                    return start;
                }
                if (bChars[j] == aChars[i + j]) {
                    if (start == -1) {
                        start = i;
                    }
                    j++;
                } else {
                    start = -1;
                }
            }
        }
        return start;
    }

    //树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(1 + maxDepth(root.left), 1 + maxDepth(root.right));
    }


}
