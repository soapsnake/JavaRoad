package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-07 15:39
 */
public class Question95 {

    //草,真他吗难懂
    //https://youtu.be/SHp-uB4ngkU

    public static void main(String[] args) {
        Question95 question95 = new Question95();
        System.out.println(question95.generateTrees(5));
    }

    //DP解法,这个实在看不懂...
    public static List<TreeNode> generateTrees2(int n) {
        List<TreeNode>[] result = new List[n + 1];   //类似hashmap的结构
        result[0] = new ArrayList<>();
        if (n == 0) {
            return result[0];
        }

        result[0].add(null);
        for (int len = 1; len <= n; len++) {
            result[len] = new ArrayList<>();   //n等于多少就会有多少个bucket,这里相当于初始化
            for (int j = 0; j < len; j++) {
                for (TreeNode nodeL : result[j]) { //遍历,如果前面没有进行初始化这里可能会报错
                    for (TreeNode nodeR : result[len - j - 1]) {
                        TreeNode node = new TreeNode(j + 1);
                        node.left = nodeL;
                        node.right = clone(nodeR, j + 1);
                        result[len].add(node);
                    }
                }
            }
        }
        return result[n];
    }

    private static TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }

    /**
     * Input: 3
     * Output:
     * [
     * [1,null,3,2],
     * [3,2,null,1],
     * [3,1,null,null,2],
     * [2,1,3],
     * [1,null,2,null,3]
     * ]
     */
    public List<TreeNode> generateTrees(int n) {
        return genTrees(1, n);
    }

    public List<TreeNode> genTrees(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);  //这里为什么要add(null)
            return list;
        }

        if (start == end) {
            list.add(new TreeNode(start));
            return list;
        }

        //左右两个子树
        List<TreeNode> left;
        List<TreeNode> right;
        for (int i = start; i <= end; i++) {
            left = genTrees(start, i - 1);  //左子树从0 -> i
            right = genTrees(i + 1, end);   //右子树从i -> end

            for (TreeNode lnode : left) {   //一次循环中所有可能的左子节点
                for (TreeNode rnode : right) { //一次循环中所有可能的右子节点
                    TreeNode root = new TreeNode(i);  //一次循环中的根节点
                    root.left = lnode;
                    root.right = rnode;
                    list.add(root);
                }
            }
        }
        return list;
    }
}
