package com.soapsnake.algorithms.leetcode.binarytree;

public class Question331 {


    /**
     * Example 1:
     * Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
     * Output: true
     * <p>
     * Example 2:
     * Input: "1,#"
     * Output: false
     * <p>
     * 判定一个字符串是不是一个二叉树的先序便利结果(先根遍历)
     *
     * 解法含义: 二叉树的任何一个节点(除了根节点),如果这个节点不为null,那么它会有两个出度(两个子节点),一个入度(父节点),
     * 如果这个节点为null节点,那么这个节点的出度为0(不会有子节点),入度为1
     * 这里设置一个变量diff,初始化根节点值为1,任意一个节点,我们都减一,代表入度都为1,如果节点不为null,那么加上两个出度
     * 根据以上规则,对于任意一个二叉树,最终的diff应该保持为0才对
     */
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int edges = 1;
        for(int i = 0; i < nodes.length; i++){
            edges--; // consume one edge
            if(edges < 0) return false; // to prevent the case: [#,](https://leetcode.com/problems/powx-n) a, ...
            if(!nodes[i].equals("#")){
                edges += 2; // generate 2 edges
            }
        }
        return edges == 0;
    }

}