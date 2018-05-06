package com.ld.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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

/**     对称树
 *            3
 *        /     \
 *      20       20
 *     /  \     /  \
 *   7    15   15   7
 **/
    public static TreeNode makeSymmetric(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(20);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(15);

        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = makeTree();

        List<List<TreeNode>> lists = layerTravse(treeNode);

        List<TreeNode> vals = frontTravse(treeNode);

        List<TreeNode> vals2 = middleTravse(treeNode);
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

    //前序遍历   根 --> 左孩 --> 右孩
    public static List<TreeNode> frontTravse(TreeNode root){
        List<Integer> vals = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();

        //前序遍历时,stack用来存放右子节点,当所有的左子节点遍历结束,弹栈即可实现所有右子节点的遍历
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                if (cur.right != null){
                    stack.push(cur.right);
                }
                vals.add(cur.val);
                nodes.add(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()){
                cur = stack.pop();
            }
        }
        System.out.print("前序遍历: ");
        ArrayUtils.printList(vals);
        return nodes;
    }

    //中序遍历:   左孩  --> 根 --> 右孩
    public static List<TreeNode> middleTravse(TreeNode root){
        //TODO debug
        List<Integer> vals = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();

        //中序遍历时stack用来存放根节点
        Stack<TreeNode> temp = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !temp.isEmpty()){
            while (cur != null){
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

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
