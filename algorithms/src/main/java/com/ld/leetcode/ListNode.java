package com.ld.leetcode;

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
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
