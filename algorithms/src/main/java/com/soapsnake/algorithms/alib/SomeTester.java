package com.soapsnake.algorithms.alib;

import com.soapsnake.algorithms.structures.list.ListNode;
import com.soapsnake.algorithms.structures.tree.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SomeTester {

    ListNode fakehead = new ListNode();
    ListNode cur = fakehead;
    final Integer test;

    public SomeTester() {
        test = 1;
    }
//
//    public SomeTester(ListNode haha, Integer heihei) {
//        this.cur = haha;
//        this.test = heihei;
//    }

    public static void main(String[] args) {
        SomeTester treeTester = new SomeTester();
        ListNode.printListNode(treeTester.treeToList(TreeNode.makeBinerSearchTree()));
    }

    //求子数组的最大和
    public static int maxSubArr(int[] arr) {
        //思路:只有在一种情况下是需要重新制定开始指针的:和小于0的时候,其他情况都应该继续累加

        if (arr == null || arr.length == 0) {
            return 0;
        }
        int total = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int cur = total + arr[i];
            if (cur < 0) {
                total = 0;
            } else {
                total += arr[i];
                if (total > max) {
                    max = total;
                }
            }
        }
        return max;
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

    @Test
    public void testMaxSub() {
        int[] arr = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(maxSubArr(arr));
    }

    @Test
    public void testfindAllSum() {
        System.out.println(findAllSum(TreeNode.makeNormalTreeFor437(), 18));
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
    public void testlayerTravse() {

        List<List<TreeNode>> lists = layerTravse(TreeNode.makeBinerSearchTree());

        for (List list : lists) {
            System.out.println(list);
        }
    }


    //判断两个链表是否相交
    public static boolean isFuckedList(ListNode list1, ListNode list2) {
        ListNode cur1 = list1;
        while (cur1 != null) {

            ListNode cur2 = list2;
            while (cur2 != null) {
                if (cur1.next == cur2) {
                    return true;
                }
                cur2 = cur2.next;
            }
            cur1 = cur1.next;
        }
        return false;
    }

}
