package org.tree;

import java.util.Stack;

public class OrderedBinaryTree {
	
	private Node root;
	
	/**
	 * �ǵݹ����ڵ㣬�������ظ��ڵ�
	 * @param key
	 */
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
	
	/**
	 * �ݹ�ز���ڵ�
	 * @param key
	 * @param node Ҫ����Ϊ�գ����ڵ㲻��ͨ���˷�������
	 */
	public void insert_recursive(int key, Node node){
		if(node==null){
			root = new Node(key);
			return;
		}
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
		
	}
	
	/**
	 * �ݹ������ڵ����ֵ
	 * @param key
	 * @param node
	 * @return
	 */
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
	/**
	 * ����������ݹ飩
	 * @param node
	 */
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
	/**
	 * ����������ǵݹ飩,���㷨�Ǵ���ģ���������ѭ�������ظ�ѹ����ѹ����Ԫ�أ������֧ѭ��
	 */
	@Deprecated
	public void in_order_without_recursion(){
		if(root==null)
			return;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		root.visited = true;
		while(!stack.isEmpty()){
			Node top = stack.peek();
			if(top.left!=null&&!top.left.visited){
				stack.push(top.left);
				top.left.visited = true;
			}else{
				top = stack.pop();
				System.out.println(top.data);
				if(top.right!=null&&!top.right.visited){
					stack.push(top.right);
					top.right.visited = true;
				}else{
					if(!stack.isEmpty())
						System.out.println(stack.pop().data);
				}
			}
		}
	}
	/**
	 * ����������ǵݹ飩
	 */
	public void in_order_without_recursion1(){
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			Node top = stack.peek();
			if(top!=null){
				if(top.visited){
					System.out.println(stack.pop().data);
					stack.push(top.right);
				}else{
					stack.push(top.left);
				}
			}else{
				stack.pop();
				if(!stack.isEmpty()){
					stack.peek().visited = true;
				}
			}
		}
	}
	/**
	 * ����������ǵݹ飩
	 */
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

//		//��ʽһ
//		for(int i=0;i<arr.length;i++){
//			tree.insert(arr[i]);
//		}
//		//��ʽ��
//		tree.root = new Node(arr[0]);
//		for(int i=1;i<arr.length;i++){
//			tree.insert_recursive2(arr[i],tree.root);	
//		}
		//��ʽ��
		tree.root = new Node(arr[0]);
		for(int i=0;i<arr.length;i++){
			tree.insert_recursive(arr[i],tree.root);	
		}
		
//		//������ʽһ
//		tree.in_order_with_recursion(tree.root);
		//������ʽ��
		//tree.in_order_without_recursion1();
		//������ʽ��
		//tree.in_order_without_recursion2();
		tree.in_order_without_recursion();
		
	}

}
class Node implements TreeComparator{
	public Node left;
	public Node right;
	public int data;
	public Boolean visited=false;
	
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