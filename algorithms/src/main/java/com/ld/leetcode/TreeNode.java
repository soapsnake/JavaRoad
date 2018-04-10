package com.ld.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    /**
     *         3
     *        /\
     *      9   20
     *         / \
     *      15    7
     *          /  \
     *        8     12
     *      / \     / \
     *    6    9  10   22
     *           /  \
     *         4     14
     * @return
     */
    public static TreeNode makeTree(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(12);
        root.right.right.left = new TreeNode(8);
        root.right.right.left.left = new TreeNode(6);
        root.right.right.left.right = new TreeNode(9);
        root.right.right.right.right = new TreeNode(22);
        root.right.right.right.left = new TreeNode(10);
        root.right.right.right.left.left = new TreeNode(4);
        root.right.right.right.left.right = new TreeNode(14);
        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = makeTree();
        List<List<TreeNode>> lists = layerTravse(treeNode);
        int i = 1;
        for (List<TreeNode> list : lists){
            for (TreeNode node : list){
                System.out.print(node.val + " , ");
            }
            System.out.println("第 " + i++  +" 层打印完毕");
        }
    }

    //层状遍历二叉树
    public static List<List<TreeNode>> layerTravse(TreeNode root){

        List<List<TreeNode>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }

        int cur = 0;
        int last = 1;
        List<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (cur < que.size()){
            last = que.size();
            List<TreeNode> list2 = new ArrayList<>();
            while (cur < last){
                TreeNode curNode = que.get(cur);
                list2.add(curNode);
                if (curNode.left != null){
                    que.add(curNode.left);
                }
                if (curNode.right != null){
                    que.add(curNode.right);
                }
                cur++;
            }
            lists.add(list2);
        }
        int i = 1;
        for (List<TreeNode> list : lists){
            for (TreeNode node : list){
                System.out.print(node.val + " , ");
            }
            System.out.println("第 " + i++  +" 层打印完毕");
        }
        return lists;
    }

    public static void frontTravse(TreeNode root){

    }

    public static void middleTravse(TreeNode root){

    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
