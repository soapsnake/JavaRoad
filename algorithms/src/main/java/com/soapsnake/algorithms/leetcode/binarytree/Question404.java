package com.soapsnake.algorithms.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/2 23:07
 */
public class Question404 {
    int sum = 0;
    List<Integer> res = new ArrayList<>();
    boolean isLeft = false;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Question404 question404 = new Question404();
        System.out.println(question404.sumOfLeftLeaves(root));
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfsRecur(root);
        return this.sum;
    }

    //先根遍历递归写法
    private void dfsRecur(TreeNode root) {
        res.add(root.val);
        System.out.println(root.val + " -> ");

        if (root.left != null) {
            isLeft = true;   //往左走了,下一个节点要么是左叶子,要么左非叶子
            dfsRecur(root.left);
        }
        if (root.right != null) {
            isLeft = false;  //往右走了,下一个节点要么右叶子,要么右非叶子
            dfsRecur(root.right);
        }

        //叶子节点且是左叶子节点
        if (root.left == null && root.right == null && isLeft) {
            sum += root.val;
        }

    }
}
