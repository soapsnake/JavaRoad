package com.soapsnake.algorithms.alib;

import com.soapsnake.algorithms.structures.tree.Node;
import com.soapsnake.algorithms.structures.tree.TreeNode;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeTester {

    //求树节点的最大距离
    int max = 0;

    public static void main(String[] args) {
        TreeTester treeTester = new TreeTester();
        Node.layerPrint(treeTester.makeTree());
    }

    public Node makeTree() {
        int layer = rand();
        Node root = new Node(rand());
        root.children = makeChildrens(1, layer);
        return root;
    }

    private List<Node> makeChildrens(int current, int layer) {
        List<Node> list = new ArrayList<>();
        if (current >= layer) {
            return list;
        }
        int num = rand();
        for (int i = 0; i < num; i++) {
            Node node = new Node(rand());
            node.children = makeChildrens(current + 1, layer);
            list.add(node);
        }
        return list;
    }

    public int rand() {
        return RandomUtils.nextInt(1, 10);
    }

    public int findMaxRange(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            //到这里说明root不为null, 但是root是叶节点了
            return 1;
        }

        int left = findMaxRange(root.left);
        int right = findMaxRange(root.right);
        max = Math.max(left + right, max);
        return Math.max(left, right) + 1;
    }

    @Test
    public void testFindMaxrange() {
        System.out.println(findMaxRange(TreeNode.makeNormalTreeFor236()));
    }


}
