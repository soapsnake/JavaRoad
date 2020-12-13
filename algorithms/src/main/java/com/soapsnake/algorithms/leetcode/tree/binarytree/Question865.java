package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;
import javafx.util.Pair;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-12-12
 */
public class Question865 {

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return this.helper(root).getValue();
    }

    private Pair<Integer, TreeNode> helper(TreeNode root) {
        if (root == null) {
            return new Pair<>(0, root);
        }
        Pair<Integer, TreeNode> l = helper(root.left);
        Pair<Integer, TreeNode> r = helper(root.right);

        int d1 = l.getKey();
        int d2 = r.getKey();
        return new Pair<>(Math.max(d1, d2) + 1, d1 == d2 ? root : d1 > d2 ? l.getValue() : r.getValue());
    }

}
