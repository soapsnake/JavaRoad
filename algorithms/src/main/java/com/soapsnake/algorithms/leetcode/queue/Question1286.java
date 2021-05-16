package com.soapsnake.algorithms.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

import com.soapsnake.algorithms.structures.tree.TreeNode;

import jdk.nashorn.internal.objects.NativeJava;
import sun.print.SunMinMaxPage;

/**
 * 
 * Created on 2020-08-14
 */
public class Question1286 {

    class CombinationIterator {
        Queue<String> queue;

        public CombinationIterator(String characters, int combinationLength) {
            queue = new LinkedList<>();
            combinations(characters, 0, "", combinationLength, queue);
        }

        public void combinations(String characters, int start, String soFar, int k, Queue<String> queue) {
            if (k == 0) {
                queue.add(soFar);
                return;
            }

            for (int i = start; i < characters.length(); i++) {
                combinations(characters, i + 1, soFar + characters.charAt(i), k - 1, queue);
            }
        }

        public String next() {
            return queue.poll();
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

        //滑动窗口最大值数组
     public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length < k) {  //错点①
                return new int[] {};   //错点②
            }
            LinkedList<Integer> queue = new LinkedList<>();
            int[] res = new int[nums.length - k + 1];
            for (int i = 0; i < nums.length; i++) {
                while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                    queue.pollLast();
                }
                queue.add(i);
                if (queue.peek() < i) {  //错点③
                    queue.poll();
                }
                if (i + 1 > k) {   //错点④
                    res[i + 1 - k] = queue.peek();  //错点⑤
                }
            }
            return res;
        }
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        this.dfs(root, low, high);
        return sum;
    }
    int sum = 0;
    private void dfs(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        dfs(root.left, low, high);
        dfs(root.right, low, high);
    }

}
