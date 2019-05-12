package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.datastructures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Question173 {


    /**
     * BSTIterator iterator = new BSTIterator(root);
     * iterator.next();    // return 3
     * iterator.next();    // return 7
     * iterator.hasNext(); // return true
     * iterator.next();    // return 9
     * iterator.hasNext(); // return true
     * iterator.next();    // return 15
     * iterator.hasNext(); // return true
     * iterator.next();    // return 20
     * iterator.hasNext(); // return false
     */
    static class BSTIterator {

        private Integer currentIndex = 0;
        private List<Integer> values = new ArrayList<>();

        public BSTIterator(TreeNode root) {
            this.toList(root);
        }

        //精髓:把二叉搜索树转成list,二叉搜索树的中序遍历结果一定是有序的
        private void toList(TreeNode root) {
            if (root == null) {
                return;
            }
            toList(root.left);
            values.add(root.val);
            toList(root.right);
        }

        /** @return the next smallest number */
        public int next() {
            int res = 0;
            res =  values.get(currentIndex);
            currentIndex++;
            return res;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return currentIndex < values.size();
        }
    }

    public static void main(String[] args) {
        TreeNode node = TreeNode.makeNormalTreeFor173();
        BSTIterator bstIterator = new BSTIterator(node);
        while (bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }
    }
}
