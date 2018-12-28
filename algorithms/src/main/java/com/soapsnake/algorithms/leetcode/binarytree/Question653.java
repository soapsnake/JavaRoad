package com.soapsnake.algorithms.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author soapsnake
 * @date 2018/11/22
 */
public class Question653 {

    public boolean findTarget(TreeNode root, int k) {
        //todo 利用排序数组来解决这个问题
        if (root == null) {
            return false;
        }

        List<Integer> res = new ArrayList<>();
        this.recursiveTree(root, res);

        for (int left = 0, right = res.size() - 1; right > left; ) {
            if (res.get(left) + res.get(right) == k) {
                System.out.println("left = " + res.get(left) + " right= " + res.get(right));
                return true;
            }
            if (res.get(left) + res.get(right) > k) {
                right--;
                continue;
            }
            if (res.get(left) + res.get(right) < k) {
                left++;
            }
        }
        return false;
    }

    private void recursiveTree(TreeNode root, List<Integer> integers) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            integers.add(cur.val);
            cur = cur.right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.makeBinerSearchTree();
        Question653 question653 = new Question653();
        System.out.println(question653.findTarget(root, 5));
    }
}
