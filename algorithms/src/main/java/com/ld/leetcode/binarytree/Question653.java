package com.ld.leetcode.binarytree;

import java.util.List;

/**
 * @author soapsnake
 * @date 2018/11/22
 */
public class Question653 {

    public boolean findTarget(TreeNode root, int k) {
        //todo 利用排序数组来解决这个问题



        return false;
    }

    private void recursiveTree(TreeNode root, List<Integer> integers) {



    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.makeBinerSearchTree();
        Question653 question653 = new Question653();
        System.out.println(question653.findTarget(root, 4));
    }
}
