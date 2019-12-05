package com.soapsnake.algorithms.leetcode.normaltree;

import com.soapsnake.algorithms.leetcode.array.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//多叉树的先根遍历(前序遍历)
class Question589 {
    private List<Integer> res = new ArrayList<>();

    public static void main(String[] args) {
        Node node5 = new Node(5, null);
        Node node6 = new Node(6, null);
        List<Node> node3children = Arrays.asList(node5, node6);
        Node node3 = new Node(3, node3children);

        Node node2 = new Node(2, null);
        Node node4 = new Node(4, null);

        Node root = new Node(1, Arrays.asList(node3, node2, node4));

        Question589 question589 = new Question589();

//        ArrayUtils.printList(question589.preorder(root));

        ArrayUtils.printList(question589.preorder2(root));

    }

    //递归版本实现
    public List<Integer> preorder(Node root) {
        if (null == root) {
            return res;
        }
        res.add(root.val);
        if (root.children == null || root.children.isEmpty()) {
            return res;
        }
        for (Node node : root.children) {
            preorder(node);
        }
        return res;
    }

    //todo 循环版本实现
    public List<Integer> preorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            root = stack.pop();
            list.add(root.val);
            if (root.children != null) {
                for (int i = root.children.size() - 1; i >= 0; i--) {
                    stack.push(root.children.get(i));
                }
            }
        }
        return list;
    }

    public List<Integer> preOrder3(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            root = stack.pop();
            list.add(root.val);
            if (root.children != null) {
                for (int i = root.children.size() - 1; i >= 0; i--) {
                    stack.push(root.children.get(i));
                }
            }
        }
        return list;
    }


}
