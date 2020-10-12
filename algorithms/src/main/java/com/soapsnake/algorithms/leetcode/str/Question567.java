package com.soapsnake.algorithms.leetcode.str;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created on 2020-04-27
 */
public class Question567 {

    //leetcode567
    public boolean checkInclusion(String s1, String s2) {
        //思路,记录s1中每个字符出现的次数,在s2上滑动一个窗口,如果窗口内的字符串能把s1的
        //字符map的值全部归0,就表明找到了符合要求的字符串
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2)
            return false;

        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (allZero(count))
            return true;

        //窗口的长度是固定的,恒等于s1的长度
        //窗子在s2上滑动,每右移一次,就会有一个新字符进入窗口,一个旧的字符移除窗口
        //对于新进入的字符,减1的意思是排除掉一个公共字符,加一的意思是引入一个公共字符
        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;
            if (allZero(count))
                return true;
        }
        return false;
    }

    //校验数组所有数字均为0
    private boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0)
                return false;
        }
        return true;
    }

    //二叉树是否有满足条件的路径
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return dfs(root,   arr, 0);
    }

    private boolean dfs(TreeNode root, int[] arr, int index) {
        if (root == null || index >= arr.length || arr[index] != root.val) {
            return false;
        }
        if (root.left == null || root.right == null) {
            return index + 1 == arr.length;
        }
        return dfs(root.left, arr, index + 1) || dfs(root.right, arr, index + 1);
    }

    /**
     * [0,1,0,0,1,0,null,null,1,0,0]
     * [0,0,1]
     */
    public static void main(String[] args) {
        Question567 question567 = new Question567();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(0);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.left.left.right = new TreeNode(1);

        root.left.right.left = new TreeNode(0);
        root.left.right.right = new TreeNode(0);
        root.right.left = new TreeNode(0);

        int[] arr = {0, 1, 1};
        System.out.println(question567.isValidSequence(root, arr));


        List<String> re = new ArrayList<>();
        re.add("lrer");
        re.add("fdas");
        re.toString();
    }
}
