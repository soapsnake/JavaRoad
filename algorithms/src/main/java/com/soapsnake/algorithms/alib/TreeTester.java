package com.soapsnake.algorithms.alib;

import com.soapsnake.algorithms.structures.list.ListNode;
import com.soapsnake.algorithms.structures.tree.Node;
import com.soapsnake.algorithms.structures.tree.TreeNode;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.Validate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    ListNode fakehead = new ListNode();
    ListNode cur = fakehead;
    //二叉查找树转排序链表
    public ListNode treeToList(TreeNode root) {
        //二叉查找树的中序遍历结果一定是有序的!!!!!
        TreeNode.layerTravse(root);
        dfsTree(root);
        return fakehead.next;
    }

    private void dfsTree(TreeNode root) {
        if (root == null) {
            return;
        }
        dfsTree(root.left);
        cur.next = new ListNode(root.val);
        cur = cur.next;
        dfsTree(root.right);
    }

    //二叉树找到所有和为指定值的路径
    public static List<List<Integer>> findAllSum(TreeNode root, int sum) {
        //思路: dfs算法:
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfsTree2(root, sum, res, new ArrayList<>());
        for (List list : res) {
            System.out.println("res = " + list);
        }
        return res;
    }

    private static void dfsTree2(TreeNode parent, int remain, List<List<Integer>> res, List<Integer> tmp) {
        System.out.println("remaiin = " + remain);
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            res.add(new ArrayList<>(tmp));
        } else {
            if (parent.left == null && parent.right == null) {
                return;
            }
            tmp.add(parent.val);
            dfsTree2(parent.left != null ? parent.left : parent.right, remain - parent.val, res, tmp);
        }
    }

    public static List<List<TreeNode>> layerTravse(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<TreeNode>> res = new ArrayList<>();
        List<TreeNode> lines = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            lines = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                lines.add(node);
            }

            for (TreeNode node : lines) {
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(lines);
        }
        return res;
    }
    @Test
    public void testfindAllSum() {
        System.out.println(findAllSum(TreeNode.makeNormalTreeFor437(), 18));
    }

    @Test
    public void testlayerTravse() {

        List<List<TreeNode>> lists = layerTravse(TreeNode.makeBinerSearchTree());

        for (List list : lists) {
            System.out.println(list);
        }
    }

    //判断树A是不是B的子树
    public boolean isSubTree(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return false;
        }
        return dfsTree(a, b);
    }

    private boolean dfsTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        } else if (a == null) {
            return false;
        } else if (b == null) {
            return false;
        } else if (a.val == b.val) {
            return dfsTree(a.left, b.left) && dfsTree(a.right, b.right);
        } else {
            return dfsTree(a, b.left) || dfsTree(a, b.right);
        }
    }

    @Test
    public void testIsSubTree() {
        TreeNode a = TreeNode.makeNormalTreeFor110();
        TreeNode b = TreeNode.makeNormalTreeFor110v2();
        System.out.println(isSubTree(b, a));
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
           return  searchBST(root.left, val);
        } else {
           return searchBST(root.right, val);
        }
    }

    @Test
    public void testSearchBst() {
        TreeNode root = null;
        int val = 2;
        searchBST(root, val);
    }

    public List<List<TreeNode>> layerTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<TreeNode>> res = new ArrayList<>();
        List<List<Integer>> res2 = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> layer = new ArrayList<>();
            List<Integer> layer1 = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    continue;
                }
                layer.add(cur);
                layer1.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(layer);
            res2.add(layer1);
        }
        System.out.println(res2);
        return res;
    }
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return total;
    }
    private int total;
    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        total++;
        helper(root.right);
    }

    @Test
    public void printTree() {
        TreeNode root = TreeNode.makeNormalTreeFor437();
        System.out.println(layerTree(root));
    }
}
