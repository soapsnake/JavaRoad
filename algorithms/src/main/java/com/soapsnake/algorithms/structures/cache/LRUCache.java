package com.soapsnake.algorithms.structures.cache;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created on 2020-04-24
 */
public class LRUCache implements Cache<Integer, Integer>{

    class DoubleNode {
        public int val;
        public int key;
        public DoubleNode next;
        public DoubleNode prev;
        public DoubleNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;
    // tail will point to the most recently used/accessed/added node
    // head will point to the least recently used/accessed/added node
    private DoubleNode head;
    private DoubleNode tail;
    private Map<Integer, DoubleNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        DoubleNode node = map.get(key);
        if(node == null) return -1;
        // node exists in the cache

        //如果该节点不在队列的尾部的话,移动该节点到尾部
        //这里的移动用的方法很搞笑,先变量保存该节点,然后从链表中删除该节点,最后变量链到尾部
        if(node != tail) {
            //如果node不是表尾节点
            if(node == head) {
                //如果node是表头,那么表头指向下一个节点(删除该节点)
                head = head.next;
            } else {
                //节点在中间,前节点指向该节点的下一个节点
                node.prev.next = node.next;
                //下节点的前节点指向当前节点的前节点
                //双向链表的中间节点的删除非常麻烦,两队指针要动
                node.next.prev = node.prev;
            }

            // move the curr node to the end/tail
            tail.next = node;
            node.prev = tail;
            node.next = null;
            tail = node;
        }

        return node.val;
    }

    public void put(int key, int value) {
        DoubleNode node = map.get(key);
        if(node == null) {
            // add new node
            node = new DoubleNode(key, value);
            if(capacity == 0) {
                // ran out of space
                map.remove(head.key);

                //淘汰节点处在链表头,我说为啥要get时候把节点放表尾了
                head = head.next;
                capacity++;
            }
            if(head == null) {
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
            map.put(key, node);
            capacity--;
        } else {
            // update the existing node with the value
            node.val = value;
            if(node != tail) {
                if(node == head) {
                    head = head.next;
                } else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }

                //改完节点值后要把节点再放回链表尾部,这里是最安全的地方
                tail.next = node;
                node.prev = tail;
                node.next = null;
                tail = node;
            }
        }
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* capacity */ );
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }
}
