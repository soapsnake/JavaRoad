package com.soapsnake.algorithms.leetcode.binarytree;

class Question437 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.makeNormalTreeFor437();

        Question437 question437 = new Question437();
        System.out.println(question437.pathSum(treeNode, 8));
    }

    /**
     * You are given a binary tree in which each node contains an integer value.
     * Find the number of paths that sum to a given value.
     * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
     * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
     * 求的是所有和为给定值的路径的总数量
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        //dfs
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.val + preTraval(root.left) + preTraval(root.right) == sum) {
            count++;
        }
        return count;
    }

    private int preTraval(TreeNode root) {
        if (root.left != null) {
            preTraval(root.left);
        }

        if (root.right != null) {
            preTraval(root.right);
        }
        return root.val;
    }
}
