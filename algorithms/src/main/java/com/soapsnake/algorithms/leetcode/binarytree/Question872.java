package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.datastructures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author soapsnake
 * @date 2018/10/29
 *
 * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
 */
class Question872 {

    //卧槽,写完提交一遍过,吓死老子了
    //解法一的缺陷为,即使两个树不满足条件也必须全部遍历完才能知道结果
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> root1Leafs = new ArrayList<>();
        List<Integer> root2Leafs = new ArrayList<>();
        dfs(root1, root1Leafs);
        dfs(root2, root2Leafs);
        return root1Leafs.equals(root2Leafs);
    }

    private void dfs(TreeNode root, List<Integer> rootLeafs) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) { //叶子节点
            rootLeafs.add(root.val);
            return;
        }
        if (root.left != null) {       //优先遍历左孩,最终结果就是由左 -> 右
            dfs(root.left, rootLeafs);
        }
        if (root.right != null) {
            dfs(root.right, rootLeafs);
        }
    }

    //解法2,解法2使用栈,优化的地方在于,一边遍历一边比较,假如两个二叉树确实不满足,那么不需要遍历完就能知道结果
    public boolean leafSimilar2(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> s1 = new Stack<>(), s2 = new Stack<>();
        s1.push(root1); s2.push(root2);
        while (!s1.empty() && !s2.empty())      //s1和s2不为空保证了比较会一直进行下去,否则只要第一对叶子节点比完就会结束
            if (dfs(s1) != dfs(s2)) return false;
        return s1.empty() && s2.empty();
    }

    public int dfs(Stack<TreeNode> s) {
        while (true) {
            TreeNode node = s.pop();
            if (node.right != null) s.push(node.right);
            if (node.left != null) s.push(node.left);
            if (node.left == null && node.right == null) return node.val;
        }
    }
}
