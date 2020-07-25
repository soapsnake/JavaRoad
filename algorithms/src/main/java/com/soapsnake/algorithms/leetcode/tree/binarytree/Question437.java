package com.soapsnake.algorithms.leetcode.tree.binarytree;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Question437 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.makeNormalTreeFor437();
        Question437 question437 = new Question437();
        System.out.println(question437.pathSum(treeNode, 8));
    }

    //使用全局变量
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();

        //key : the prefix sum, value : how many ways get to this prefix sum
        preSum.put(0,1);
        helper(root, 0, sum, preSum);
        return count;
    }
    int count = 0;

    /**
     *
     * @param root     当前节点
     * @param currSum  当前路径和
     * @param target   目标路径和
     * @param preSum   路径和 -> 路径数
     */
    public void helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return;
        }

        currSum += root.val;

        /**
         *         例如，如果我们有currSum = 8和target = 6，我们想看看是否可以剪切一些presum为currSum-target = 2的头节点，
         *         那么我们得到了从该节点的子节点到当前节点的所需路径 。
         */
        int remain = currSum - target;  //这里为什么是currSum - target而不是反过来了???
        if (preSum.containsKey(remain)) {
            count += preSum.get(remain);
        }
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        helper(root.left, currSum, target, preSum);
        helper(root.right, currSum, target, preSum);

        // 既然都已经加到叶子节点了,说明这个sum是不可能在满足了,所以要把这个路径去掉
        preSum.put(currSum, preSum.get(currSum) - 1);
    }

        //不使用全局变量的解决方法
    public int pathSum2(TreeNode root, int sum) {

        //key : the prefix sum, value : how many ways get to this prefix sum
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0,1);
        return helper2(root, 0, sum, preSum);
    }

    public int helper2(TreeNode root, int currSum, int target, Map<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }

        currSum += root.val;    //之前的总和+当前节点值
        //res是达到当前总和的路径数
        int res = preSum.getOrDefault(currSum - target, 0);

        //到达当前总和的路径数 + 1,为什么要加1了?
        //如果之前已经存在到该路径和的路径,那么由于当前的计算得出的和又被算出来了一次,所以就相当于又找到了一条
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        //分别到当前节点的左右子节点中去进行递归
        res += helper2(root.left, currSum, target, preSum) + helper2(root.right, currSum, target, preSum);

        //这步没有看懂,是回退吗?
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
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
    public int pathSumMyOwnVersion(TreeNode root, int sum) {
        //dfs
        //我觉得这个解法完全没有问题,不知道为啥不算对,他妈的
        if (root == null) {
            return 0;
        }
        List<String> finalres = new ArrayList<>();
        this.treeDFS(0, root, sum, "", finalres, false);
//        System.out.println(finalres);
        return finalres.size();
    }

    private void treeDFS(int temp, TreeNode root, int sum, String path, List<String> finalres, boolean start) {
        if (root == null) {
            return;
        }
        if (root.val + temp == sum) {
            path = path + root.val;
            finalres.add(path);
            return;
        }

        //如果还没开始求和,那么节点就有两种选择:作为第一个节点开始进行求和, 或者不开始求和,留给子类去做选择
        if (!start) {
            //本节点不作为开始节点
            this.treeDFS(temp, root.left, sum, path, finalres, false);
            this.treeDFS(temp, root.right, sum, path, finalres, false);

            //本节点作为开始节点
            temp = temp + root.val;
            path = path + root.val + "->";
            this.treeDFS(temp, root.left, sum, path, finalres, true);
            this.treeDFS(temp, root.right, sum, path, finalres, true);
        } else {
            //如果已经开始进行求和,那么这个节点就没有选择了,必须跟进
            temp = temp + root.val;
            path = path + root.val + "->";
            this.treeDFS(temp, root.left, sum, path, finalres, true);
            this.treeDFS(temp, root.right, sum, path, finalres, true);
        }
    }


    public int myPathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        this.myHelper(root, 0, sum, preSum);
        return this.mycount;
    }
    private int mycount;
    private void myHelper(TreeNode root, int pre, int dest, Map<Integer, Integer> preSum) {
        if (root == null) {
            return;
        }
        int curSum = pre + root.val;
        int remain = curSum - dest;
        if (preSum.containsKey(remain)) {
            this.mycount += preSum.get(remain);
        }

        if (preSum.containsKey(curSum)) {
            preSum.put(curSum, preSum.get(curSum) + 1);
        } else {
            preSum.put(curSum, 1);
        }
        myHelper(root.left, curSum, dest, preSum);
        myHelper(root.right, curSum, dest, preSum);

        preSum.put(curSum, preSum.get(curSum) - 1);
    }

    //暴力搜索,这个复杂度比利用hashmap来的高
    public int pathSumDfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int dfs(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) + dfs(node.left, sum - node.val) + dfs(node.right, sum - node.val);
    }
}
