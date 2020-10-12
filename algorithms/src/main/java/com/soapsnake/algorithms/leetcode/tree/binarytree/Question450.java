package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 *
 * Created on 2020-03-07
 */
public class Question450 {

    /**
     * 二叉搜索树的前序遍历结果是单调递增的
     * 删除节点后面对四种情况
     * 1.node doesn't have left or right - return null
     * 2.node only has left subtree- return the left subtree
     * 3.node only has right subtree- return the right subtree
     * 4.node has both left and right - find the minimum value in the right subtree, set that value to the currently
     * found node, then recursively delete the minimum value in the right subtree
     */
    //leetcode450
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            //找到了要删除的节点了
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode minNode = findMin(root.right);
            //这里用minNode的value覆盖掉了root的value,隐含了删除的逻辑
            root.val = minNode.val;
            //这里为什么要删除:当把minNode的值赋给root后,该树相当于有了两个一模一样的节点,必须把原来那个干掉
            //所以就再走一次删除逻辑
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        //根据二叉搜索树的定义,左节点一定是值最小的节点
        while (node.left != null) {
            node = node.left;
        }
        //node是最左叶子节点
        return node;
    }
}
