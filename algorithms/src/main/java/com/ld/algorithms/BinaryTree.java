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
	}
	
	
	public BinaryTree() {
	this.rootnode = rootnode;
	this.node = node;
	}
	
	public int getCountNumber(){
		//return countNumber;
		return 0;
	}
	

}
