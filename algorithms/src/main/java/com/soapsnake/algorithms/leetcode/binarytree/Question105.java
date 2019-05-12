package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.datastructures.tree.TreeNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-09 23:13
 */
public class Question105 {

    /**
     * 假设我们有两个数组,pre和in数组
     * 先序遍历的规则表明pre[0]就是树的根节点
     * 所以我们可以先在in数组中找到pre[0],假设是in[5] (数组不会出现重复数字)
     * 现在我们知道了in[5]是根节点,那么in[0]-in[5]就是左子节点,而in[6]-结束就是右子节点
     * 在子数组中进行递归,pre[1]就是左子树的根节点,假设是in[3],那么in[3]是左子树的根节点,而in[0]-in[2]就是左子树,而in[4]-in[5]就是右子树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) { //先序数组遍历完 || 中序开始超过了结束时退出
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);  //每一次这个root都是左子树(或者右子树)的根节点
        int inIndex = 0; // Index of current root in inorder  ,每一次都需要重置
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;   //找出子树的根节点在in数组中的索引,这个索引是用来区分左右子树的关键
                break;
            }
        }
        //左根节点的索引依次增大     始终是0  ->    左子树右边界
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);

        //(inIndex - inStart + 1)是一个偏移量,他表示的是出现在root左子树中的元素个数 //inIndex + 1 -> end 右子树的边界
        root.right = helper(preStart + (inIndex - inStart + 1), inIndex + 1, inEnd,  preorder, inorder);
        return root;
    }

    public static void main(String[] args) {
        Question105 question105 = new Question105();
        int[] pre = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode.layerTravse(question105.buildTree(pre, inorder));


    }
}
