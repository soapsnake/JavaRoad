package com.soapsnake.algorithms.leetcode.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soapsnake.algorithms.structures.tree.TreeNode;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-03-28
 */
public class Question508 {

    //leetcode508
    Map<Integer, Integer> count = new HashMap<>(); // subtotal -> count
    int maxCount = 0;  //total出现的最大次数

    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        List<Integer> res = new ArrayList<>();
        for (int s : count.keySet()) {
            //取所有count达到maxCount的subtotal
            if (count.get(s) == maxCount)
                res.add(s);
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    private int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int s = dfs(root.left) + dfs(root.right) + root.val;
        count.put(s, count.getOrDefault(s, 0) + 1);
        maxCount = Math.max(maxCount, count.get(s));
        return s;
    }
}
