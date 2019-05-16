package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-21 21:22
 */
public class Question501 {

    int maxcount = 0;
    Integer prev = null;
    int count = 1;
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> nodes = new ArrayList<>();
        this.dfs(root, nodes);
        int[] res = new int[nodes.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = nodes.get(i);
        }
        return res;
    }

    private void dfs(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }
        dfs(root.left, nodes);
        if (prev != null) { //这题最关键就是这个null判断
            if (root.val == prev) {
                ++count;
            } else {
                count = 1;
            }
        }
        if (count > maxcount) {
            maxcount = count;
            nodes.clear();
            nodes.add(root.val);
        } else if (count == maxcount) {
            nodes.add(root.val);
        }
        prev = root.val;
        dfs(root.right, nodes);
    }

    public static void main(String[] args) {
//        TreeNode node = TreeNode.makeBinerSearchTree();

        Map<String, Integer> jiushi = new HashMap<>();
        jiushi.put("idjisa", null);

        TreeNode node = new TreeNode(2147483647);
//        node.left = new TreeNode(1);
//        node.right = new TreeNode(3);
        Question501 question501 = new Question501();
        System.out.println(Arrays.toString(question501.findMode(node)));
    }
}
