package com.ld.alib;

/**
 *1、随机生成一个长度为N的无序链表，节点的value在0-10之间，删除链表中重复的节点（链表需要自己创建）。
 2、实现：自定义一个链表数据结构（可以是单链表或双向链表），然后初始化一个链表数据，
 并对该链表实现两两翻转（是翻转整个节点，而不是仅交换节点的值），然后输出翻转之后的结果。
 比如构造的链表是：1->2->3->4->5，翻转之后，输出：2->1->4->3->5.
 构造一个链表，比如1->2->3->4....，两两节点互转，即调整为：2->1->4->3....（不能直接交换节点的value来实现）
 */
public class NodeList {

    private Node node;

    private Node next;

    private Node head;

    private Node tail;

    public NodeList(Node node){
        this.head = node;
        this.tail = node;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public void addNode(Node node){
        this.tail.next = node;
        this.tail = node;
    }

    public void delNode(Node node){
        Node list = node;
        Node cur;
        while (list.next != null){
            cur = this.head;
            if (cur.next.equals(node)){
                cur.next = node.next;
                break;
            }
            list = list.next;
        }
    }
    public void reverse() {
        Node list = this.head;
        Node cur = list;
        while (list.next != null) {
            //指针每移动一次,需要把该节点的next节点的next节点指向该节点
            cur = list.next;
            Node temp = cur.next;
            list = cur.next;
            list.next = temp;
            list = list.next;
        }
    }




}
