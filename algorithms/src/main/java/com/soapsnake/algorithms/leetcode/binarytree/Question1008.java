package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.List;

/**
 *
 * Created on 2020-04-20
 */
public class Question1008 {

    //leetcode1008
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, Integer.MAX_VALUE);
    }
    int i = 0;
    public TreeNode bstFromPreorder(int[] A, int bound) {
        if (i == A.length || A[i] > bound) {
            return null;
        }
        TreeNode root = new TreeNode(A[i++]);
        root.left = bstFromPreorder(A, root.val);
        root.right = bstFromPreorder(A, bound);
        return root;
    }


    interface BinaryMatrix {
        int get(int x, int y) ;
        List<Integer> dimensions();
 };
    //leftMostColumnWithOne
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> lis = binaryMatrix.dimensions();
        int res = -1;
        int x = lis.get(0) - 1;
        int y = lis.get(1) - 1;
        while (x >= 0 && y >= 0) {
            if (binaryMatrix.get(x, y) == 1) {
                res = y;
                y -= 1;
            } else {
                x -= 1;
            }
        }
        return res;
    }
}
