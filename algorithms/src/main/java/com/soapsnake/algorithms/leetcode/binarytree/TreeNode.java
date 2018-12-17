package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.leetcode.array.ArrayUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    /**
     * 普通二叉树
             *         3
             *      /    \
             *     9      20
             *    /      /  \
             *   8      15   7
            *   /\            \
           *   6  9           12
          *   /    \          / \
         *   4     14       10  22
     *
     * @return
     */
    public static TreeNode makeNormalTree() {
        TreeNode root = new TreeNode(3);

        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(12);
        root.right.right.right.right = new TreeNode(22);
        root.right.right.right.left = new TreeNode(10);

        root.left = new TreeNode(9);
        root.left.left = new TreeNode(8);
        root.left.left.left = new TreeNode(6);
        root.left.left.right = new TreeNode(9);
        root.left.left.left.left = new TreeNode(4);
        root.left.left.right.right = new TreeNode(14);
        return root;
    }

    /**
     * 对称树
     *        3
     *     /    \
     *   20      20
     *  /  \     /  \
     * 7    15  15   7
     **/
    public static TreeNode makeSymmetricTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(20);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(15);

        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }

    /**
     *
     * 二叉搜索树
     *       5
     *      / \
     *     3   6
     *    /\    \
     *   2  4    7
     */
    public static TreeNode makeBinerSearchTree() {
            TreeNode root = new TreeNode(5);

            root.left = new TreeNode(3);
            root.left.left = new TreeNode(2);
            root.left.right = new TreeNode(4);

            root.right = new TreeNode(6);
            root.right.right = new TreeNode(7);
            return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = makeNormalTree();
        List<TreeNode> lists1 = frontTravseWhile(treeNode);

//        List<List<TreeNode>> lists = layerTravse(treeNode);
//
//        List<TreeNode> vals = frontTravseWhile(treeNode);
//
//        List<TreeNode> vals2 = middleTravseWhile(treeNode);
    }

    //层状遍历迭代版
    //@see:Question102 完全自己手写
    public static List<List<TreeNode>> layerTravse(TreeNode root) {

        List<List<TreeNode>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        int cur = 0;
        int last = 1;
        List<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (cur < que.size()) {
            last = que.size();
            List<TreeNode> list2 = new ArrayList<>();
            while (cur < last) {
                TreeNode curNode = que.get(cur);
                list2.add(curNode);
                if (curNode.left != null) {
                    que.add(curNode.left);
                }
                if (curNode.right != null) {
                    que.add(curNode.right);
                }
                cur++;
            }
            lists.add(list2);
        }
        int i = 1;
        for (List<TreeNode> list : lists) {
            for (TreeNode node : list) {
                System.out.print(node.val + " , ");
            }
            System.out.println("第 " + i++ + " 层打印完毕");
        }
        return lists;
    }

    //层状遍历递归版本
    //link: https://leetcode.com/problems/binary-tree-level-order-traversal-ii/discuss/34981/My-DFS-and-BFS-java-solution
    public static List<List<TreeNode>> layerTravseRecursive(TreeNode root) {
        //todo
        return null;
    }


        //前序遍历   根 --> 左孩 --> 右孩
    public static List<TreeNode> frontTravseWhile(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();
        //前序遍历时,stack用来存放右子节点,当所有的左子节点遍历结束,弹栈即可实现所有右子节点的遍历
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                nodes.add(cur);
                vals.add(cur.val);
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                cur = cur.left;
            }
            cur = stack.pop();
            nodes.add(cur);
            vals.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            cur = cur.left;
        }
        ArrayUtils.printList(vals);
        return nodes;
    }

    //中序遍历:   左孩  --> 根 --> 右孩
    public static List<TreeNode> middleTravseWhile(TreeNode root) {
        //TODO debug
        List<Integer> vals = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();

        //中序遍历时stack用来存放根节点
        Stack<TreeNode> temp = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !temp.isEmpty()) {
            while (cur != null) {
                temp.add(cur);
                cur = cur.left;
            }
            cur = temp.pop();
            vals.add(cur.val);
            nodes.add(cur);
            cur = cur.right;
        }
        System.out.print("中序遍历: ");
        ArrayUtils.printList(vals);
        return nodes;
    }

    //todo 先序遍历递归实现
    public static List<TreeNode> frontTravseRecursive(TreeNode root) {

        return null;
    }

    //todo 中序遍历递归实现
    public static List<TreeNode> middleTravseRecursive(TreeNode root) {
        return null;
    }

    //TODO 右序遍历递归实现
    public static List<TreeNode> rightTravseRecursive(TreeNode root) {
        return null;
    }


    /**
     *437题的二叉树
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     */
    public static TreeNode makeNormalTreeFor437() {
        TreeNode root = new TreeNode(10);
        //left
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);

        //right
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);
        return root;
    }

    //todo 二叉查找树节点插入
    public boolean insertIntoBinerSearchTree(TreeNode node) {
        return false;
    }

    //todo 二叉查找树节点删除
    public boolean removeFromBinerSearchTree(TreeNode node) {
        return false;
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
