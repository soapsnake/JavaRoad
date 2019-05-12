package com.soapsnake.algorithms.leetcode.binarytree;

import com.soapsnake.algorithms.datastructures.tree.TreeNode;
import com.soapsnake.algorithms.datastructures.list.ListNode;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-04-10 18:45
 */
public class Question109 {

    /**
     * Given the sorted linked list: [-10,-3,0,5,9],
     * <p>
     * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     * <p>
     * 0
     * / \
     * -3   9
     * /   /
     * -10  5
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return toBST(head, null);
    }

    public TreeNode toBST(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        if (head == tail) return null;  //list已经遍历到了尾部
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //到这里,fast指向的一定是list的尾节点,而slow一定指向的是list的中间节点

        TreeNode thead = new TreeNode(slow.val);  //中间节点成为了树的根
        thead.left = toBST(head, slow);   //这里传了slow节点当做tail节点,其实list已经被一分为二了,前半部分是左子树
        thead.right = toBST(slow.next, fast);  //slow是中间节点,总感觉这里应该传fast的(因为fast此时指向的是tail节点),list的后半截是右子树
        return thead;
    }
}
