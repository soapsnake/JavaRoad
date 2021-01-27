package com.soapsnake.algorithms.leetcode.dp;

import com.soapsnake.algorithms.structures.cache.LRUCache;
import com.soapsnake.algorithms.structures.list.ListNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class Question5 {

    /**
     * 动态规划算法
     */
    private int lo;       //最长子字符串左索引
    private int maxLen;  //最长子字符串最大长度(右边界的偏移量)

//    public static void main(String[] args) {
//        Question5 question5 = new Question5();
////        String str1 = "babad";
//        String str2 = "efabcbaefehiabcba";
//        System.out.println(question5.longestPalindromeDPSolution(str2));
//    }

    //答案正确,但是解法复杂度过高,n平方,因为只能追加写,所以就是
    public String longestPalindrome(String s) {
        if (s == null) return "";
        if (s.equals("")) return "";
        if (s.equals(" ")) return "";
        if (s.length() == 1) return s;
        int max = Integer.MIN_VALUE;
        String maxStr = "";
        StringBuilder sb;
        for (int i = 0; i < s.length(); i++) {
            if (s.length() - i < max) return maxStr;   //最大长度不可能超过max了,后面不算了
            sb = new StringBuilder();
            for (int j = i; j < s.length(); j++) {
                sb.insert(0, s.charAt(j));
                if (sb.length() == 1) continue;
                if (s.substring(i, j + 1).equals(sb.toString())) {
                    if (sb.length() > max) {
                        max = sb.length();
                        maxStr = sb.toString();
                    }
                }
            }
        }
        if (maxStr.equals("")) {
            return s.charAt(0) + "";
        } else {
            return maxStr;
        }
    }

    //https://leetcode.com/problems/longest-palindromic-substring/solution/
    //中文解释可见:https://www.felix021.com/blog/read.php?2040

    //解法复杂度还是过高
    public String longestPalindrome2(String s) {
        if (s == null) return "";
        if (s.equals("")) return "";
        if (s.equals(" ")) return "";
        if (s.length() == 1) return s;
        int leftStr = 0;
        int rightStr = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; i - j >= 0; j++) {
                leftStr = j;
                rightStr = i - j;
                String s1 = s.substring(leftStr, s.length() - rightStr);
                if (this.checkPalindrome(s1))
                    return s1;
            }
        }
        return s.charAt(0) + "";
    }

    //校验字符串是否回文结构
    public boolean checkPalindrome(String source) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < source.length(); i++) {
            sb.insert(0, source.charAt(i));
        }
        return source.equals(sb.toString());
    }

    public String longestPalindromeDPSolution(String s) {
        if (s.length() < 2) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            this.extendPalindrome(s, i, i);   //以第i个字符为中心点往两边扩散,那么子字符串长度将为奇数
            this.extendPalindrome(s, i, i + 1); //以第i和i+1个字符为中心点往两边扩散,那么子字符串长度将为偶数
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() - 1 && s.charAt(left) == s.charAt(right)) { //左右指针不是聚拢而是往两边扩散
            left--;
            right++;
        }
        if (maxLen < right - left - 1) {  //能进这个if说明这个子字符串的长度更加的长
            lo = left + 1;
            maxLen = right - left - 1;
        }
    }

    public int findKthPositive(int[] arr, int k) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] - (mid + 1) >= k) {
                r = mid;  //missed more or equal than k numbers, left side;
            } else {
                l = mid + 1;   // missed less than k numbers, must be in the right side;
            }
        }
        return l + k;
    }

    /**
     * Input: arr = [2,3,4,7,11], k = 5
     * Output: 9
     *
     * Input: arr = [1,2,3,4], k = 2
     * Output: 6
     */
    @Test
    public void testFindk() {
        int[] arr = {2,3,4,7,11};
        int k = 5;
        System.out.println(findKthPositive(arr, k));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) {
            return null;
        }
        int left = 0, right = lists.length - 1;
        return this.helper(left, right, lists);
    }

    private ListNode helper(int left, int right, ListNode[] lists) {
        if (left == right) {
            return lists[left];
        }
        if (left + 1 == right) {
            return this.mergeTwoList(lists[left], lists[right]);
        }
        int mid = left + (right - left) / 2;
        ListNode leftList = this.helper(left, mid - 1, lists);
        ListNode rightList = this.helper(mid, right, lists);
        return this.mergeTwoList(leftList, rightList);
    }

    private ListNode mergeTwoList(ListNode leftList, ListNode rightList) {
        if (leftList == null && rightList == null) {
            return null;
        }
        ListNode lp = leftList;
        ListNode rp = rightList;
        ListNode fakeHead = new ListNode(0);
        ListNode cur = fakeHead;
        while (lp != null && rp != null) {
            if (lp.val <= rp.val) {
                cur.next = lp;
                lp = lp.next;
            } else {
                cur.next = rp;
                rp = rp.next;
            }
            cur = cur.next;
        }
        if (lp != null) {
            cur.next = lp;
        } else {
            cur.next = rp;
        }
        return fakeHead.next;
    }
}
