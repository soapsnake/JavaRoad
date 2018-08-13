package com.ld.alib;

/**
 *1、随机生成一个长度为N的无序链表，节点的value在0-10之间，删除链表中重复的节点（链表需要自己创建）。
 2、实现：自定义一个链表数据结构（可以是单链表或双向链表），然后初始化一个链表数据，
 并对该链表实现两两翻转（是翻转整个节点，而不是仅交换节点的值），然后输出翻转之后的结果。
 比如构造的链表是：1->2->3->4->5，翻转之后，输出：2->1->4->3->5.
 构造一个链表，比如1->2->3->4....，两两节点互转，即调整为：2->1->4->3....（不能直接交换节点的value来实现）
 */
public class NodeList {

    //单向链表
    public static class Node {
        public int value;
        public Node next; //下一个节点

        public Node(int value){
            this.value = value;
        }

        @Override
        public boolean equals(Object o){
            if (!(o instanceof Node)){
                return false;
            }
            return this.value == ((Node) o).value;
        }

        @Override
        public String toString(){
            return String.valueOf(this.value);
        }

    }

    public Node head;  //表头

    public Node tail; //表尾

    public int size;

    public NodeList(){}

    public NodeList(Node node) {
        this.head = node;
        this.tail = node;
    }

    //打印链表
    private String printList(){
        String res = "";
        Node temp = this.head;
        while (temp != null){
            System.out.print(temp.value);
            res += temp.value;
            res += "->";
            if (temp.next != null){
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println(" :链表size="+this.size);
        return res;
    }

    public static String printList(NodeList list){
        String res = "";
        Node temp = list.head;
        while (temp != null){
            System.out.print(temp.value);
            res += temp.value;
            res += "->";
            if (temp.next != null){
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println(" :链表size="+list.size);
        return res;
    }

    //添加节点
    public void addNode(Node node) {
        this.size += 1;
        if (null == this.head && null == this.tail){
            //空链表
            this.head = node;
            this.tail = node;
            return;
        }

        this.tail.next = node;
        this.tail = node;
    }

    //删除给定节点
    public void delNode(Node node) {
        if (!this.containsNode(node)){
            return;
        }
        Node pointer = this.head;
        while (pointer.next != null){
            if (pointer.equals(node)){
                //头节点就是要删除的节点
                this.head = pointer.next;
            }
            if (pointer.next.equals(node)){
                pointer.next = pointer.next.next;
            }
            pointer = pointer.next;
        }
    }

    //是否包含指定节点
    public boolean containsNode(Node node) {
        Node temp = this.head;
        while (temp.next != null){
            if (temp.equals(node)){
                return true;
            }
            temp = temp.next;
        }
        System.out.println("当前链表不包含该值为"+node.value+"的节点");
        return false;
    }

    //链表全反转
    public static void reverse(NodeList list) {
        Node head = list.head;
        Node prev = head;
        Node cur = head;
        Node temp = null;
        while(cur != null){
            temp = cur.next;
            cur.next = prev;

            cur = cur.next;
        }
        head.next = prev;
    }

    //链表两两反转
    public void reverseTriple(){

    }

    //删除重复节点
    public void deleteDuplicate() {
        Node man = this.head;
        Node kuai = man;
        while (man.next != null){
            kuai = man;
            while (kuai.next != null){
                if (kuai.next.equals(man)){   //探测到了重复
                    kuai.next = kuai.next.next;
                    this.size--;
                    continue;
                }
                kuai = kuai.next;
            }
            man = man.next;
        }
    }


    @Override
    public String toString(){
        return this.printList();
    }
}
