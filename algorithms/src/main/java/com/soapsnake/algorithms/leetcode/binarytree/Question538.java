package com.soapsnake.algorithms.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

import com.soapsnake.algorithms.leetcode.array.ArrayUtils;
import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * Given a Binary GraphSearch Tree (BST), convert it to a Greater Tree such that every key of the original BST
 * is changed to the original key plus sum of all keys greater than the original key in BST.
 */
class Question538 {

    int sum = 0;
    List<Integer> res = new ArrayList<>();

    public static void main(String[] args) {
        Question538 question538 = new Question538();
        TreeNode node = TreeNode.makeBinerSearchTree();
        TreeNode newTree = question538.convertBST(node);
        TreeNode.layerTravse(newTree);
    }

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        this.backOrder(root);
        ArrayUtils.printList(res);
        return root;
    }

    private void backOrder(TreeNode root) {
        if (root.right != null) {
            backOrder(root.right);
        }

        root.val += sum;  //本节点加上个节点的值
        sum = root.val;   //sum保存本节点的值(实际上是一个累加的值)
        res.add(root.val);
        if (root.left != null) {
            backOrder(root.left);
        }
    }

}
