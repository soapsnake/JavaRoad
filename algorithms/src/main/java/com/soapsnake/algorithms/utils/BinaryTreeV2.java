package com.soapsnake.algorithms.utils;

/**
 * Created by soapsnake on 2018/1/19.
 */
public class BinaryTreeV2 {

    //根节点
    private Node root;
    //树高度
    private int hight;
    //节点数量
    private int count;

    public BinaryTreeV2() {

    }

    public static void main(String[] args) {
        BinaryTreeV2 v2 = new BinaryTreeV2();
        v2.addNode(18);
        v2.addNode(4);

        v2.addNode(10);
        v2.addNode(6);

        v2.addNode(5);
        v2.addNode(15);
        v2.addNode(14);

        System.out.println("前序遍历:");
        v2.frontSort();
        System.out.println("\n");
//
//        System.out.println("后序遍历:");
//        v2.backSort();

        int des = 100;
        System.out.println(v2.selectOne(des) == null ? "没查到" : v2.selectOne(des).number);

    }

    /**
     * 将输入的数字构造成一颗二叉搜索树
     *
     * @param element
     * @return
     */
    public BinaryTreeV2 buildTree(int[] element) {
        //element为空

        //elemnt只有一个元素

        //对数组进行排序

        //二分查找,获取中间数,并设置为树的根

        //调add方法依次插入其他元素
        return this;
    }


    /**
     * 增
     * 如果按照二叉搜索树的定义,所有左子节点的值必须小于根,右子节点的值必须大于根,
     */
    public boolean addNode(int value) {
        Node node = new Node();
        node.number = value;

        if (root == null) {
            root = node;
            return true;
        }
        return this.insert(node, root);
    }

    //递归式添加新节点
    private boolean insert(Node node, Node dest) {
        if (node.number == dest.number) {
            System.out.println("要插入的节点已经存在");
            return false;
        }

        //①插入前首先就需要确定插入位置,从根节点开始比较
        if (node.number > dest.number) {
            if (dest.rightSon != null) {
                insert(node, dest.rightSon);
            } else {
                dest.rightSon = node;
                return true;
            }
        }

        if (node.number < dest.number) {
            if (dest.leftSon != null) {
                insert(node, dest.leftSon);
            } else {
                dest.leftSon = node;
                return true;
            }
        }
        return false;
    }

    /**
     * 删
     * 如果是二叉搜索树,这个他妈的有点难
     */
    public boolean delete(int value) {
        return removeNode(value, root);
    }

    private boolean removeNode(int number, Node node) {

        return false;
    }


    /**
     * 改
     */
    public boolean update(Node node) {
        return false;
    }

    /**
     * 查单个
     */
    public Node selectOne(int value) {
        return selectOne(value, root);
    }

    private Node selectOne(int value, Node node) {
        if (node == null) {
            return null;
        }

        if (value == node.number) {
            return node;
        }

        if (value > node.number) {
            return selectOne(value, node.rightSon);
        } else {
            return selectOne(value, node.leftSon);
        }
    }

    /**
     * 前序遍历
     */
    public void frontSort() {
        this.frontSort(root);
    }

    private void frontSort(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.number + ",");

        if (node.leftSon != null) {
            frontSort(node.leftSon);
        }

        if (node.rightSon != null) {
            frontSort(node.rightSon);
        }
    }

    /**
     * 后续遍历
     */
    public void backSort() {
        this.backSort(root);
    }

    private void backSort(Node node) {
        if (node == null) {
            return;
        }

        if (node.leftSon != null) {
            backSort(node.leftSon);
        } else {
            System.out.println(node.number);

            if (node.parent.rightSon != null) {
                System.out.println(node.parent.rightSon.number);
            }
            backSort(node.parent);
        }

//        if (node.rightSon != null){
//            backSort(node.rightSon);
//        }else {
//
//        }
    }

    /**
     * 中序遍历
     */
    public void midSort() {
    }

    /**
     * 广度优先遍历
     */
    public void guangSort() {
    }

    public static class Node {
        //简化版data,只存储一个数字
        public int number;
        public Node leftSon;
        public Node rightSon;
        public Node parent;
    }


}


