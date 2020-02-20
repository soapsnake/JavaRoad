package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

public class Question1315 {

    //leetcode1315
    public int sumEvenGrandparent(TreeNode root) {
        //求所有祖父节点为偶数的节点的和
        return helper(root, 1, 1);
    }

    //递归算法和栈有非常多相识的地方,对于树来说,当递归到达node==null时,就代表
    //我们已经到达了栈顶也就是树的叶子节点,这时候进行弹栈,就相当于是自底向上沿着树在移动.
    public int helper(TreeNode node, int p, int gp) {
        if (node == null) return 0;
        return helper(node.left, node.val, p) + helper(node.right, node.val, p) + (gp % 2 == 0 ? node.val : 0);
    }
}
