package com.soapsnake.algorithms.leetcode.normaltree;

import com.soapsnake.algorithms.leetcode.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author soapsnake
 * @date 2018/11/7
 */
class Question897 {
    private List<TreeNode> nodes = new LinkedList<>();
    private List<Integer> integers = new LinkedList<>();

    public TreeNode increasingBST2(TreeNode root) {
        if (root == null) {
            return null;
        }
        //把树里面的节点按大小序插入链表
        this.insertList(root);
        if (root.left != null) {
            increasingBST(root.left);
        }
        if (root.right != null) {
            increasingBST(root.right);
        }

        TreeNode newRoot = nodes.get(0);
        int i = 1;
        while (i < nodes.size()) {
            newRoot.right = nodes.get(i++);
            newRoot = newRoot.right;
        }
        return newRoot;
    }

    //二分法插入
    private void insertList(TreeNode root) {
        int mid = nodes.size() / 2;
        while (true) {
            if (nodes.get(mid).val == root.val) {
                nodes.add(mid + 1, root);  //中间
            }
            if (nodes.get(mid).val > root.val) {
                mid = mid / 2;
                continue;
            }
            mid = (nodes.size() - mid) / 2;
        }
    }

    //递归版二分
    private int binerSearch(int[] arr, int dest) {
        return this.search(0, arr.length - 1, arr, dest);
    }

    private int search(int left, int right, int[] arr, int dest) {
        int mid = (right + left) / 2;
        if (dest == arr[mid]) {
                return mid;
        }
        if (dest > arr[mid]) {
          return search(mid + 1, right, arr, dest);
        }
        return search(0, mid, arr, dest);
    }

    public static void main(String[] args) {
        Question897 question897 = new Question897();
        int[] dest = {1,2,3,4,5,6,7,8,9,13,15};   //9的索引为8

        int target = 1;
        long start1 = System.nanoTime();
        System.out.println(question897.binerSearch(dest, target));
        long end1 = System.nanoTime() - start1;

        long start2 = System.nanoTime();
        System.out.println(question897.iterateBS(dest, target));
        long end2 = System.nanoTime() - start2;

        System.out.println("cost1 = " + end1 + " cost2 = " + end2);
    }

    //迭代版二分
    private int iterateBS(int[] arr, int dest) {
        int mid = arr.length / 2;
        while (true) {
            if (arr[mid] == dest) {
                return mid;
            }
            if (arr[mid] > dest) {  //二分查找最主要是观察mid能不能取到边界值
                mid = mid / 2;
                continue;
            }
            if (arr[mid] < dest) {   //右侧
                mid = (arr.length + mid) / 2;
            }
        }
    }

    private void insertList2(int val) {
        if (integers.size() == 0) {
            integers.add(val);
            return;
        }
        if (integers.size() == 1) {
            if (integers.get(0) >= val) {
                integers.add(0, val);
            } else {
                integers.add(val);
            }
        }

        int size = integers.size();
        int mid = size / 2;
        while (true) {
            if (mid == 1) {
                integers.add(1, val);
            }
            if (integers.get(mid) == val) {
                integers.add(mid + 1, val);
                return;
            }
            if (integers.get(mid) > val) {
                mid = mid / 2;
                System.out.println("mid = mid / 2, new mid = " + mid);
                continue;
            }
            mid = (size - mid) / 2;
            System.out.println("(size - mid) / 2, new mid = " + mid);
        }
    }

//    public static void main(String[] args) {
//        Question897 question897 = new Question897();
//        List<Integer> arr = Arrays.asList(2,3,4,5,6,7,8,9,10,23,5,8);
//        arr.forEach(question897::insertList2);
//        ArrayUtils.printList(question897.integers);
//    }

    private TreeNode newRoot;
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (newRoot == null) {
            newRoot = root;
        }
        TreeNode cur = newRoot;
        while (cur.right != null) { //todo 思路就是把原链表中的node插到新链表的合适位置
            if (cur.val <= root.val && root.val < cur.right.val) {   //链表的指针运算太恶心了
                TreeNode temp = cur.right;
                cur.right = root;
                cur.right.right = temp;
                break;
            }
            cur = cur.right;
        }

        if (root.left != null) {
            increasingBST(root.left);
        }
        if (root.right != null) {
            increasingBST(root.right);
        }
        return newRoot;
    }

//    public static void main(String[] args) {
//        Question897 question897 = new Question897();
//        TreeNode.frontTravseWhile(question897.increasingBST(TreeNode.makeNormalTree()));
//    }

}
