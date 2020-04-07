package com.soapsnake.algorithms.leetcode.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 *
 * Created on 2020-03-05
 */
public class Question449 {


    /**
     * Serialization is the process of converting a data structure or object into a sequence of bits
     * so that it can be stored in a file or memory buffer, or transmitted across a network connection
     * link to be reconstructed later in the same or another computer environment.
     */
    //LeetCode449, 二叉树的序列化/反序列化(多叉树思路类似)
    public static class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        //先根遍历序列化,空节点统一用"#"表示,采用","作为节点分隔符
        public void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("#").append(",");
            } else {
                sb.append(root.val).append(",");
                serialize(root.left, sb);
                serialize(root.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
            return deserialize(q);
        }

        //先根遍历反序列化
        public TreeNode deserialize(Queue<String> q) {
            String s = q.poll();
            if (s.equals("#")) return null;
            TreeNode root = new TreeNode(Integer.parseInt(s));
            root.left = deserialize(q);
            root.right = deserialize(q);
            return root;
        }
    }


}
