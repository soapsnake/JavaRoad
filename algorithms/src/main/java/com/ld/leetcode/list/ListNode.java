package com.ld.leetcode.list;

public class ListNode {
    public int val;
    public ListNode next;
    private ListNode last;
    public ListNode(int x) {
        val = x;
        last = this;
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

    public void add(int val){
        ListNode newNode = new ListNode(val);
        this.addNode(newNode);
    }

    public void addNode(ListNode node){
        if (node == null){
            return;
        }
        if (this.last == null){
            last = node;
            return;
        }

        last.next = node;
        last = node;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public static ListNode revertList(ListNode node){
        if (node == null){
            return null;
        }

        ListNode head;
        ListNode newList = null;
        while (node != null){
            head = new ListNode(node.val);
            head.next = newList;
            newList = head;
            node = node.next;
        }
        return newList;
    }



    public static void main(String[] args) {
        ListNode node = new ListNode(1);

//        node.next = new ListNode(2);
//
//        node.next.next = new ListNode(3);
//        printListNode(node);
//        System.out.println(" ");
//
//        printListNode(null);
        ListNode node1 = new ListNode(2);
        node.addNode(node1);
        printListNode(node);

        ListNode node2 = new ListNode(1);
        node2.add(2);
        node2.add(3);
        node2.add(4);

        ListNode.printListNode(ListNode.revertList(node2));

    }
}
