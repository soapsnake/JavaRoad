package com.ld.alibaba;

import java.util.Random;

public class ListTest {


    public static void main(String[] args) {
        Random random = new Random();
        Node node = new Node(random.nextInt(10));
        NodeList nodeList = new NodeList(node);
        int size = 20;
        for (int i = 1; i<size;i++){
            Node node1 = new Node(random.nextInt(10));
            nodeList.addNode(node1);
        }
        //删除重复几点:
        deleteDuplicate(nodeList);
        nodeList.reverse();
    }

    private static void deleteDuplicate(NodeList nodeList) {
        if (nodeList == null)
            return;
        Node temp = nodeList.getHead();
        int dest = temp.value;
        Node cur;
        while (temp.next != null){
            cur = temp;
            if(cur.value == dest){
                nodeList.delNode(cur);
            }
            temp = temp.next;
        }
        nodeList.delNode(nodeList.getHead());
        deleteDuplicate(nodeList);
    }


}
