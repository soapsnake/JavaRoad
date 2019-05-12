package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.datastructures.tree.TreeNode;

/**
 * @author soapsnake
 * @date 2018/11/4
 * Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree,
 * so the result should return the new root of the trimmed binary search tree.
 */
class Question669 {

    public TreeNode trimBST2(TreeNode root, int L, int R) {
//        List<TreeNode> res = new ArrayList<>();
//        Stack<TreeNode> stack = new Stack<>();
//
//        TreeNode cur = root;                      //中序遍历,关键就是这个cur指针
//        while (cur != null || !stack.isEmpty()){
//            while (cur != null) {
//                stack.push(cur);
//                System.out.println("栈压入 node = " + cur.val);
//                cur = cur.left;
//            }
//                cur = stack.pop();
//                System.out.println("栈弹出 node = " + cur.val);
//
//                res.add(cur);
//                cur = cur.right;
//            }
//
//            for (TreeNode node : res) {
//                System.out.println(node.val);
//            }

            if (root.val > L && root.val < R) {

                TreeNode left = root.left;
                while (left.val < L) {
                    left = left.left;
                }

                TreeNode right = root.right;
                while (right.val > R) {
                    right = right.right;
                }
            }
            return null;
    }

    //递归版本解法
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;

        //如果根值比左区间还小,那么到右侧去找
        if (root.val < L) return trimBST(root.right, L, R);
        //如果根值比右区间还大,那么到左侧去找
        if (root.val > R) return trimBST(root.left, L, R);

        //那么现在逻辑到了这里,说明这个节点刚好处于L和R之间
        //那么分别传递root的左子节点和右子节点进一步缩小范围,不符合的范围将被cut(这里其实涉及到了二叉搜索树的delete操作,还需进一步研究)
        //https://www.youtube.com/watch?v=gcULXE7ViZw
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }

    public static void main(String[] args) {
        Question669 question669 = new Question669();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(7);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(8);
        root.left.left.left = new TreeNode(4);
        root.left.left.left.left = new TreeNode(2);

        root.right = new TreeNode(15);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(17);

        TreeNode.middleTravseWhile(question669.trimBST(root, 6, 15));
    }
}
