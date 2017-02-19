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
	
	
	//get方法总入口
	public Value getValue(Key key){
		return getValue(key,rootnode);
	}
	
	//查,根据key查所在的node
	public Value getValue(Key key,Node node){      //重构，key，node走这个方法
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
	
	
	public Node put(Key key,Value value){
		return put(key, value, rootnode);
	}
	
	//增．改
	public Node put(Key key,Value value,Node node){
		if (node == null) {                               //假如传入的左右node是空的，那么直接新建一个左或者右node返回
			return  new Node(key,value,1);
		}
		
		int com = key.compareTo(node.key);   
		if (com == 0) {                   //二叉树中已经存在对应该key的node,此时直接修改对应节点的value
			node.value = value;
		}
		
		if (com >0) {
			node.right =  put(key, value, node.right);
		}
		
		else{
			node.left =  put(key, value, node.left);
		}
		
		node.value = value;
		node.countNumber = size(node.right)+size(node.left)+1;
		return node;
	}
	
	//删
	public void delete(Key key){
		
		
	}
	
	public int rank(){
		return 0;
	}
	
}
