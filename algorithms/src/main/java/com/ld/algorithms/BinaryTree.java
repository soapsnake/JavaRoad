package com.ld.algorithms;

public class BinaryTree<Key extends Comparable<Key>,Value>{
	private Node rootnode;
	private Node node;
	
	private class Node{
		public Key key;
		private Value value;
		private Node left;
		private Node right;
		private int countNumber;      //base on this root-node,how many node do this have
		
		public Node(Key key,Value value,int countNumber) {
			this.key = key;
			this.value = value;
			this.countNumber = countNumber;
		}
	}
	
	public BinaryTree(Node rootnode, Node node) {
	this.rootnode = rootnode;
	this.node = node;
	}
	
	//容量
	public int size(){
		return size(rootnode);
	}
	
	public int size(Node node){
		return node.countNumber;
	}
	
	
	//get方法API入口
	public Value getValue(Key key){
		return getValue(key,rootnode);
	}
	
	//查,根据key查所在的node
	private Value getValue(Key key,Node node){      //重构，key，node走这个方法
		if (node == null) {    // 传进来的node为空
			return null;
		}
		
		int cop = key.compareTo(node.key);
		if (cop == 0) {
			return node.value;
		}
		
		else if (cop > 0) {
				return getValue(key, node.right);
		}
		
		else  {
				return getValue(key, node.left);
		}
	}
	
	
	//增,改API入口
	public Node put(Key key,Value value){
		return put(key, value, rootnode);
	}
	
	//增．改
	private Node put(Key key,Value value,Node node){
		if (node == null) {                               //假如传入的左右node是空的，那么直接新建一个左或者右node返回
			return  new Node(key,value,1);
		}
		
		int com = key.compareTo(node.key);   
		if (com == 0) {                   //二叉树中已经存在对应该key的node,此时直接修改对应节点的value
			node.value = value;
		}
		
		if (com >0) {       
			node.right =  put(key, value, node.right);        //递归的最终的结果有两个:1.左右子树为空,那会得到一个新建的左右子树
			              																								//2.左右子树的key正好等于要插的这个key,这种情况返回的就是你传入的左右子树
		}
		
		else{
			node.left =  put(key, value, node.left);
		}
		
		node.countNumber = size(node.right)+size(node.left)+1;
		return node;
	}
	
	//删
	public void delete(Key key){
		
		Value value  = getValue(key);
		if (value == null) {
			System.out.println("二叉树中并不存在含有这个key的node");
			return;
		}
		
	}
	
	//key最小的节点
	public Node minNode(){
		return null;
	}
	
	//key最大的节点
	public Node maxNode(){
		return null;
	}
	
	//返回排名为k的节点的key值
	public Key select(int k){
		return null;
	}
	
	//含某key的节点的排名
	public int rank(Key key){
		return 0;
	}
	
}
