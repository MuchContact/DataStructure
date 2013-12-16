package org.tree;

public class TrieTree {
	
	private TrieNode root;
	private int counter;
	
	public TrieTree() {
		root = new TrieNode();
	}
	/**
	 * ����һ���µĵ���
	 * @param word
	 */
	public void insertWord(String word){
		TrieNode cursor = root;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			int index = c - 'a';
			//����ýڵ㲻���ڣ����´���һ���ڵ�
			if(cursor.subNodes[index]==null){
				cursor.subNodes[index] = new TrieNode();
			}
			//�����ͷ���������ڵ��һ���������
			if(i==word.length()-1){
				cursor.subNodes[index].terminal = true;
				break;
			}
			
			cursor = cursor.subNodes[index];
		}
	}
	/**
	 * @param keyword
	 * @param topSize
	 */
	public void search(String keyword, int topSize){
		TrieNode node = findNode(keyword);
		
		getTopMatchedWords(node,topSize,keyword);
		//������Ϻ�����
		counter = 0;
	}
	/**
	 * ������ָ���ؼ���ƥ��Ľڵ�λ��
	 * @param keyword
	 * @return
	 */
	public TrieNode findNode(String keyword){
		TrieNode cursor = root;
		for(int i=0;i<keyword.length();i++){
			int index = keyword.charAt(i)-'a';
			if(cursor.subNodes[index]==null){
				cursor = null;
				break;
			}
			cursor = cursor.subNodes[index];
		}
		return cursor;
	}
	
	/**
	 * ��ȡĳһ�ڵ��µ�����ǰtopSize�����ʣ�����ж���topSize�����ʵĻ�
	 * @param node
	 * @param topSize
	 * @param prex
	 */
	public void getTopMatchedWords(TrieNode node, int topSize, String prex){
		if(counter>topSize-1){
			//counter����
			return;
		}
		if(node==null){
			return;
		}
		if(node.terminal){
			System.out.println(prex);
			counter++;
		}
		for(int i=0;i<26;i++){
			if(node.subNodes[i]!=null){
				char c = (char) ('a'+i);
				String newPrex = prex+c;
				getTopMatchedWords(node.subNodes[i],topSize,newPrex);	
			}
		}
	}
	
	public static void main(String arg[]){
		TrieTree tree = new TrieTree();
		tree.insertWord("good");
		tree.insertWord("god");
		tree.insertWord("go");
		tree.insertWord("goar");
		tree.insertWord("going");
		tree.insertWord("gone");
		tree.search("go",5);
		System.out.println("########another#########");
		tree.search("goa",5);
		
	}
	
}
class TrieNode{
	public TrieNode[] subNodes;
	public Boolean terminal=false;
	public TrieNode() {
		subNodes = new TrieNode[26];
		for(int i=0;i<26;i++){
			subNodes[i] = null;
		}
	}
	
}