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

    @Test
    public void testPreOrder() {
        postOrder2(TreeNode.makeBinerSearchTree());
    }

    //先序遍历,先根节点,然后左孩,然后右孩
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " -> ");
        preOrder(root.left);
        preOrder(root.right);
    }

    //中序遍历,先左孩,然后根,然后右节点
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " -> ");
        inOrder(root.right);
    }

    //后序遍历,先左孩,然后右孩,最后根
    public List<Integer> postOrder(TreeNode root) {
        postOrder2(root);
        return postlist;
    }
    public void postOrder2(TreeNode root) {
        if (root == null){
            return;
        }
        postOrder2(root.left);
        postOrder2(root.right);
        System.out.print(root.val + " -> ");
        postlist.add(root.val);
    }
    List<Integer> postlist = new ArrayList<>();


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
        int left = findMaxRange(root.left);
        int right = findMaxRange(root.right);
        max = Math.max(left + right, max);
        return Math.max(left, right) + 1;
    }

    @Test
    public void testFindMaxrange() {
        System.out.println(findMaxRange(TreeNode.makeNormalTreeFor129()));
    }


}
