package com.soapsnake.algorithms.leetcode.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import lombok.experimental.Helper;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-24
 */
public class Question646 {

    /**
     * Example 1:
     * Input: [[1,2], [2,3], [3,4]]
     * Output: 2
     * Explanation: The longest chain is [1,2] -> [3,4]
     */
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        System.out.println(Arrays.deepToString(pairs));
        int sum = 0, n = pairs.length, i = -1;
        while (++i < n) {
            sum++;
            int curEnd = pairs[i][1];
            while (i + 1 < n && pairs[i + 1][0] <= curEnd) {
                i++;
            }
        }
        return sum;
    }

    @Test
    public void test() {
        int[][] paris = {{-10, -8}, {8, 9}, {-5, 0}, {6, 10}, {-6, -4}, {1, 7}, {9, 10}, {-4, 7}};
        System.out.println(findLongestChain(paris));
    }

    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    public int sum(TreeNode root, int prev) {
        if (root == null) {
            return 0;
        }
        if (root.right == null && root.left == null) {
            //只有在root是叶子节点的情况下,才会累加
            return prev * 10 + root.val;
        }
        return sum(root.left, prev * 10 + root.val) + sum(root.right, prev * 10 + root.val);
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(0);
        System.out.println(sumNumbers(root));
    }
}
