package com.ld.leetcode.list;

public class ListNode{
    public int val;
    public ListNode next;
    public ListNode tail; //尾节点
    public ListNode head; //头节点

    public ListNode() {

    }

    public ListNode(int val){
        this.val = val;
    }

    public static void printListNode(ListNode root){
        if (root == null){
            System.out.println("null list!!!!");
            return;
        }
        int size = 1;
        System.out.print(root.val + " , ");
        while (root.next != null){
            root = root.next;
            size++;
            if (root.next == null){
                System.out.print(root.val);
            }else {
                System.out.print(root.val + " , ");
            }
        }
        System.out.println("   ->ListSize is: " + size);
    }

    public void addNode(ListNode node){
        if (node == null){
            return;
        }
        if (this.head == null) {
            this.head = node;
        }
        if (this.tail == null){
            this.tail = node;
        }

        tail.next = node;
        tail = node;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    //删除目标节点,入参为目标节点
    public void deleteNode(ListNode node) {
        ListNode list = this.head;  //list现在指向该链表,避免使用this指针
        while (list != null){
            //如果要删除的是头节点
            if (node.equals(list)){
                list.head = list.next;
            }
            //如果下一个节点就是要删除的节点
            if (node.equals(list.next)){
                if (list.next.next != null){
                    list.next = list.next.next;
                }else {
                    //下一个节点就是尾节点
                    list.next = null;
                    list.tail = list;
                }
            }
            list = list.next;
        }
    }

    public static ListNode revertList(ListNode node){
//        if (node == null){
//            return null;
//        }
//
//        ListNode head;
//        ListNode newList = null;
//        while (node != null){
//            head = new ListNode(node.val);
//            head.next = newList;
//            newList = head;
//            node = node.next;
//        }
//        return newList;
        return null;
    }



    public static void main(String[] args) {

//        node.next = new ListNode(2);
//
//        node.next.next = new ListNode(3);
//        printListNode(node);
//        System.out.println(" ");
//
//        printListNode(null);
//        ListNode node1 = new ListNode(2);
//        node.addNode(node1);
//        printListNode(node);

        ListNode node2 = new ListNode();
//        node2.add(1);
//        node2.add(2);
//        node2.add(3);
//        node2.add(4);
        ListNode.printListNode(node2);
//        ListNode.printListNode(ListNode.revertList(node2));
        node2.deleteNode(new ListNode(2));
        ListNode.printListNode(node2);
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof ListNode)){
            return false;
        }
        return this.val == ((ListNode) o).val;
    }
}
