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
    private DoubleNode head, tail;
    private Map<Integer, DoubleNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        DoubleNode doubleNode = map.get(key);
        if(doubleNode == null) return -1;

        // node exists in the cache
        if(doubleNode != tail) {
            if(doubleNode == head) {
                head = head.next;
            } else {
                doubleNode.prev.next = doubleNode.next;
                doubleNode.next.prev = doubleNode.prev;
            }

            // move the curr node to the end/tail
            tail.next = doubleNode;
            doubleNode.prev = tail;
            doubleNode.next = null;
            tail = doubleNode;
        }

        return doubleNode.val;
    }

    public void put(int key, int value) {
        DoubleNode doubleNode = map.get(key);
        if(doubleNode == null) {
            // add new node
            doubleNode = new DoubleNode(key, value);
            if(capacity == 0) {
                // ran out of space
                map.remove(head.key);
                head = head.next;
                capacity++;
            }
            if(head == null) {
                head = doubleNode;
            } else {
                tail.next = doubleNode;
                doubleNode.prev = tail;
            }
            tail = doubleNode;
            map.put(key, doubleNode);
            capacity--;
        } else {
            // update the existing node with the value
            doubleNode.val = value;
            if(doubleNode != tail) {
                if(doubleNode == head) {
                    head = head.next;
                } else {
                    doubleNode.prev.next = doubleNode.next;
                    doubleNode.next.prev = doubleNode.prev;
                }

                tail.next = doubleNode;
                doubleNode.prev = tail;
                doubleNode.next = null;
                tail = doubleNode;
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
