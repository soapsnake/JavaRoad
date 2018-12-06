package com.soapsnake.algorithms.leetcode.normaltree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/6 00:09
 */
public class Question590 {
    List<Integer> res = new ArrayList<>();

    public List<Integer> postorder(Node root) {
        if (null == root) {
            return res;
        }

        dfsTree(root);
        return res;
    }

    private void dfsTree(Node root) {
        if (null == root) {
            return;
        }

        for (Node node: root.children) {
            dfsTree(node);
        }
        res.add(root.val);
    }


}
