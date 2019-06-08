package com.soapsnake.algorithms.structures.hashmap;

/**
 * @author soapsnake
 * @date 2018/11/25
 *
 * 本人自用hashMap
 * todo 1. 改进以求支持泛型
 *      2. 扩容机制
 *      3. 线程安全机制(ConcurrentHashMap)
 *      4. 假如该类实现Map接口,看一下还缺什么
 */
public class MyHashMap {

    private Node[] nodes;
    private int size;

    public int size() {
        return this.size;
    }

    static class Node<K, V> {
        K key;
        V val;

        Node next;
        Node prev;

        @Override
        public int hashCode() {
            if (this.key instanceof Integer) { //如果key是int型那就100个一链
                final Integer temp = (Integer) this.key;
                return temp / 100;
            }

            return this.key.hashCode();
        }
    }

    /** Initialize your data structure here. */
    public MyHashMap() {
        this.nodes = new Node[16];   //0 ~ 1600
        this.size = 0;
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        //map扩容逻辑暂不实现

        Node<Integer, Integer> node = new Node<>();
        node.key = key;
        node.val = value;
        int hashcode = node.hashCode();
        int index = hashcode % nodes.length;

        Node head = nodes[index];
        if (head == null) {
            //数组该位置无链表
            nodes[index] = node;
            this.size++;
            return;
        }

        Node tail = null;
        while (head != null) {
            Node next = head.next;
            if (next == null) {
                tail = head;
            }
            if (head.key.equals(node.key)) {
                if (head.val.equals(node.val)) {
                    System.out.println("map中已经有该KV对");
                    return;
                }
                head.val = node.val;
                return;
            }
            head = next;
        }
        //链表中无这个key,插到链表尾部
        tail.next = node;
        node.prev = tail;
        this.size++;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        Node<Integer, Integer> node = new Node<>();
        node.key = key;
        int hashcode = node.hashCode();

        int index = hashcode % this.nodes.length;
        Node head = this.nodes[index];
        if (head == null) {
            System.out.println("map中无对应该key的node");
            return -1;
        }
        while (head != null) {
            Node next = head.next;
            if (head.key.equals(node.key)) {
                return (int)head.val;
            }
            head = next;
        }
        System.out.println("map中无对应该key的node");
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        Node<Integer, Integer> node = new Node<>();
        node.key = key;
        int hashcode = node.hashCode();
        int index = hashcode % this.nodes.length;
        Node head = this.nodes[index];
        if (head == null) {
            System.out.println("map中无对应该key的node");
            return;
        }
        if (head.key.equals(node.key)) {
            this.nodes[index] = head.next;
            this.size--;
            return;
        }
        while (head.next != null) {
            Node next = head.next;
            if (head.next.key.equals(node.key)) {
                head.next = next.next;
                this.size--;
                return;
            }
            head = next;
        }
        System.out.println("map中无对应该key的node");
    }

    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println(hashMap.get(1));            // returns 1
        System.out.println(hashMap.get(3));            // returns -1 (not found)
        hashMap.put(2, 1);          // update the existing value
        System.out.println(hashMap.get(2));            // returns 1
        hashMap.remove(2);          // remove the mapping for 2
        System.out.println(hashMap.get(2));            // returns -1 (not found)
    }

}
