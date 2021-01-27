package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

import java.util.Arrays;

class Question141 {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.addNode(new ListNode(2));
        node.addNode(new ListNode(3));
        node.addNode(new ListNode(4));

        ListNode spe = new ListNode(5);

        node.next = spe;
        spe.next = new ListNode(6);
        spe.next.next = new ListNode(7);
        spe.next.next.next = spe;

        Question141 question141 = new Question141();
        System.out.println(question141.hasCycle(node));

    }

    //快慢指针探测list是否有循环,慢速指针一次一步,快速指针一次两步
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow_point = head;
        ListNode fast_point = head;

        while (fast_point.next != null && fast_point.next.next != null) {
            slow_point = head;
            fast_point = fast_point.next.next;
            head = head.next;

            if (fast_point == slow_point) {
                return true;
            }
        }
        return false;
    }

    public int maxOperations(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        Arrays.sort(nums);
        int total = 0;
        int left = 0, right = nums.length - 1;
        while (right > left) {
            if (nums[left] + nums[right] == k) {
                total++;
                left++;
                right--;
            } else if (nums[left] + nums[right] > k) {
                right--;
            } else {
                left++;
            }
        }
        return total;
    }


    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            this.helper(i, i, s);
            this.helper(i, i + 1, s);
        }
        return s.substring(start, start + maxlen);
    }

    int start = 0;
    int maxlen = 0;
    private void helper(int left, int right, String s) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (right - left - 1 > maxlen) {
            maxlen = right - left - 1;
            start = left + 1;
        }
    }

}
