package com.ld.leetcode.normaltree;

import java.util.Arrays;
import java.util.List;

/**
 * Given a n-ary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the root
 * node down to the farthest leaf node.
 */
class Question559 {

    private int max = 0;

    public static void main(String[] args) {
        Question559 question559 = new Question559();

        Node node5 = new Node(5, null);
        Node node6 = new Node(6, null);
        List<Node> node3children = Arrays.asList(node5, node6);
        Node node3 = new Node(3, node3children);

        Node node2 = new Node(2, null);
        Node node4 = new Node(4, null);

        Node root = new Node(1, Arrays.asList(node3, node2, node4));

        System.out.println(question559.maxDepth(root));
    }

    public int maxDepth(Node root) {
        if (null == root) {
            return 0;
        }

        treeDFS(root, 1);
        return max;
    }

    private void treeDFS(Node root, int depth) {
        this.max = Math.max(depth, max);   //最关键其实是这一步,利用Math.max不断刷新max的值
        if (root.children != null) {
            depth += 1;
            for (Node node : root.children) {
                treeDFS(node, depth);           //树的递归DFS算法
            }
        }
    }
}
