package com.soapsnake.algorithms.leetcode.list;

import com.soapsnake.algorithms.structures.list.ListNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-22 20:51
 */
public class Question328 {

    /**
     * 题目理解错误: 把奇序号的所有节点放在一起,所有偶序号的节点放在一起,无视节点的值
     */
    //leetcode328
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode temp = even.next;
        ListNode tempEven = even;
        int count = 3;
        while (temp != null) {
            if (count % 2 == 0) {
                even.next = temp;
                even = even.next;
            } else {
                odd.next = temp;
                odd = odd.next;
            }
            temp = temp.next;
            count++;
        }
        even.next = null;
        odd.next = tempEven;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1).next(2).next(3).next(4).next(5);
        Question328 question328 = new Question328();
        System.out.println(question328.oddEvenList(head));
    }

    //leetcode438
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        if(p.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;
        int head = 0;
        int len = Integer.MAX_VALUE;
        while(end < s.length()){
            char c = s.charAt(end);
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) counter--;
            }
            end++;
            while(counter == 0){
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == p.length()){
                    result.add(begin);
                }
                begin++;
            }
        }
        return result;
    }
}



