package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.datastructures.list.ListNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-21 11:13
 */
public class Question817 {

    /**
     * Input:
     * head: 0->1->2->3->4
     * G = [0, 3, 1, 4]
     * Output: 2
     * Explanation:
     * 0 and 1 are connected, 3 and 4 are connected,
     * so [0, 1] and [3, 4] are the two connected components.
     */
    public int numComponents(ListNode head, int[] G) {

        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> glist = new HashSet<>();
        for (int i : G) {
            glist.add(i);
        }
        List<Integer> temp = new ArrayList<>();
        while (head != null) {
            if (glist.contains(head.val)) { //如果是连接的话
                temp.add(head.val);   //追加元素至temp中
                glist.remove(head.val); //g中删除掉
            } else {
                if (!temp.isEmpty()) {
                    res.add(temp);
                    temp.clear();   //清空temp
                }
            }
            head = head.next;
        }
        if (!temp.isEmpty()) {
            res.add(temp);
        }
        return res.size() + glist.size();
    }


}
