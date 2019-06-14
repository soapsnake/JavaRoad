package com.soapsnake.algorithms.structures.cache;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 用链表的解法,复杂度过高
 *
 */
public class LRUCacheList implements Cache {
    private List<Node> list = new LinkedList<>();
    static class Node {
        int key;
        int val;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    private int limit;

    public LRUCacheList(int capacity) {
        this.limit = capacity;
    }

    public int get(int key) {
        int res = -1;
        Iterator<Node> iterator = this.list.iterator();
        Node tar = null;
        while (iterator.hasNext()) {
            Node temp = iterator.next();
            if (temp.key == key) {
                res = temp.val;
                tar = temp;
                iterator.remove();
                break;
            }
        }
        if (tar != null) {
            list.add(tar);
        }
        return res;
    }

    public void put(int key, int value) {
        int old = this.get(key); //如果存在这个旧Node会被移动到链表尾
        if (old == -1) {
            this.list.add(new Node(key, value));
            if (this.list.size() > this.limit) {
                this.list.remove(0);
            }
        } else {
            Node tar = this.list.get(this.list.size() - 1);
            tar.val = value;
        }
    }
}
