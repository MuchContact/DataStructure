package org.tree;

public class TrieTree {
	
	private TrieNode root;
	private int counter;
	
	public TrieTree() {
		root = new TrieNode();
	}
	/**
	 * 插入一个新的单词
	 * @param word
	 */
	public void insertWord(String word){
		TrieNode cursor = root;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			int index = c - 'a';
			//如果该节点不存在，则新创建一个节点
			if(cursor.subNodes[index]==null){
				cursor.subNodes[index] = new TrieNode();
			}
			//如果到头，则给这个节点加一个结束标记
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
		//搜索完毕后置零
		counter = 0;
	}
	/**
	 * 查找与指定关键字匹配的节点位置
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
	 * 获取某一节点下的所有前topSize个单词，如果有多于topSize个单词的话
	 * @param node
	 * @param topSize
	 * @param prex
	 */
	public void getTopMatchedWords(TrieNode node, int topSize, String prex){
		if(counter>topSize-1){
			//counter置零
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