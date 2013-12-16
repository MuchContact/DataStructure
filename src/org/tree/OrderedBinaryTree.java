package org.tree;

import java.util.Stack;

public class OrderedBinaryTree {
	
	private Node root;
	
	public void insert(int key){
		if(root==null){
			root = new Node(key);
			return ;
		}
		Node current = root;
		
		while(true){
			if(key>current.data){
				if(current.right==null){
					current.right = new Node(key);
					break;
				}else{
					current = current.right;
				}
			}else if(key<current.data){
				if(current.left==null){
					current.left = new Node(key);
					break;
				}else
					current = current.left;
			}else{
				break;
			}
		}
	}
	
	public void insert_recursive(int key, Node node){
		if(key>node.data){
			if(node.right==null){
				node.right = new Node(key);
			}else{
				insert_recursive(key, node.right);
			}
		}else if(key<node.data){
			if(node.left==null){
				node.left = new Node(key);
			}else{
				insert_recursive(key, node.left);
			}
			
		}
		//return node;
	}
	
	public Node insert_recursive2(int key, Node node){
		if(node==null){
			return new Node(key);
		}
		if(key>node.data){
			node.right = insert_recursive2(key,node.right);
		}else if(key<node.data){
			node.left = insert_recursive2(key, node.left);	
		}
		
		return node;
	}
	
	public void delete_node(){
		
	}
	public void in_order_with_recursion(Node node){
		if(node==null)
			return;
		in_order_with_recursion(node.left);
		System.out.println(node.data);
		in_order_with_recursion(node.right);
	}
	public void pre_order_with_recursion(){
		
	}
	public void post_order_with_recursion(){
		
	}
	public void in_order_without_recursion(){
		if(root==null)
			return;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			Node top = stack.peek();
			if(top.left!=null){
				stack.push(top.left);
			}else{
				top = stack.pop();
				System.out.println(top.data);
				if(top.right!=null){
					stack.push(top.right);
				}
			}
		}
	}
	public void in_order_without_recursion2(){
		if(root==null)
			return;
		Stack<Node> stack = new Stack<Node>();
		
		Node current = root;
		
		while(true){
			if(current!=null){
				stack.push(current);
				current = current.left;
			}else{
				if(stack.isEmpty()){
					break;
				}
				current = stack.pop();
				System.out.println(current.data);
				current = current.right;
			}
		}
	}
	public void pre_order_without_recursion(){
		
	}
	public void post_order_without_recursion(){
		
	}
	
	public void test(Node node){
		node = new Node(6);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrderedBinaryTree tree = new OrderedBinaryTree();
		int arr[]={12,67,3,78,43,13,1,24,31};
		tree.root = new Node(arr[0]);
		for(int i=1;i<arr.length;i++){
			tree.insert_recursive2(arr[i],tree.root);	
		}
		tree.in_order_with_recursion(tree.root);
		//tree.in_order_without_recursion2();
		
		
	}

}
class Node implements TreeComparator{
	public Node left;
	public Node right;
	public int data;
	
	public Node(int key) {
		data = key;
	}
	
	@Override
	public short compareTo() {
		// TODO Auto-generated method stub
		return 0;
	}
}

interface TreeComparator{
	public short compareTo();
}