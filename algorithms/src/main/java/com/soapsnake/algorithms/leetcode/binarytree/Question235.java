package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-17 22:29
 */
public class Question235 {

    //最低公共祖先
    TreeNode target = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return canReach(root, p, q);
    }

    private TreeNode canReach(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return target;
        }
        if (hasSon(root,p) && hasSon(root, q)) { //如果root是p和q的公共父节点
            target = root;   //先缓存起来
            canReach(root.left, p, q);   //继续往下搜索看有没有更低节点满足
            canReach(root.right, p, q);  //继续往下搜索看有没有更低节点满足
        }
        return target;
    }

    private boolean hasSon(TreeNode root, TreeNode node) {
        if (root == null) {
            return false;
        }
        if (root.val == node.val) {
            return true;
        }
        // 或关系是关键!!!!!
        return hasSon(root.left, node) || hasSon(root.right, node);
    }

    public static void main(String[] args) {
        TreeNode node = TreeNode.makeNormalTreeFor437();
        Question235 question235 = new Question235();
        TreeNode p = new TreeNode(3);
        TreeNode q = new TreeNode(2);
        System.out.println(question235.lowestCommonAncestor(node, p, q));
    }


}
