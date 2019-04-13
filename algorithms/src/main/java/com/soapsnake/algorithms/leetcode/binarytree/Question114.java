package com.soapsnake.algorithms.leetcode.binarytree;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-13 01:45
 */
public class Question114 {


    /**
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     *
     * The flattened tree should look like:
     *
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        // save current right for concatination
        TreeNode right = root.right;

        if (root.left != null) {
            // step 1: concatinate root with left flatten subtree
            root.right = root.left;
            root.left = null; // set left to null

            // step 2: move to the end of new added flatten subtree
            while (root.right != null)
                root = root.right;

            // step 3: contatinate left flatten subtree with flatten right subtree
            root.right = right;
        }
    }
}
