package com.soapsnake.algorithms.structures.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Node {
    public int val;

    public List<Node> children;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", children=" + children +
                '}';
    }

    public static void layerPrint(Node root) {
        if (root == null) {
            throw new RuntimeException("root不合法");
        }
        System.out.println("root = " + root.val);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Node> layer = new ArrayList<>();
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node != null)
                    layer.add(node);
            }
            for (Node node : layer) {
                System.out.print(node.val);
                System.out.print(",");
                if (node.children != null)
                    queue.addAll(node.children);
            }
            System.out.println("本层结束");
        }
    }
}
