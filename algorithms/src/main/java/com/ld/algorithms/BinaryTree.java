package com.ld.algorithms;

public class BinaryTree<Key extends Comparable<Key>,Value>{
	private Node rootnode;
	private Node node;
	
	private class Node{
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private int countNumber;
		
		public Node() {
			this.key = key;
			this.value = value;
			this.countNumber = countNumber;
			
		}
	}
	
	public BinaryTree() {
	this.rootnode = rootnode;
	this.node = node;
	}
	
	//容量
	public int size(){
		return 0;
	}
	
	//查
	public Value getValue(Key key){
		return null;
	}
	
	//增．改
	public void put(Key key,Value value){
		
	}
	
	//删
	public void delete(Key key){
		
		
	}
	
	public int rank(){
		return 0;
	}
	
}
