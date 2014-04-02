package org.algorithm.kmp;

/**
 * @author Liu hengjian
 *
 */
public class KMP {
	/**
	 * 匹配字串
	 */
	private char[] pattern = null;
	private char[] source = null;
	/**
	 * 存储前后缀最大公共字符数 
	 */
	private int [] next = null;
	
	/**
	 * @param src
	 * @param target
	 * @return 返回初始位置
	 */
	public int match(String src, String target){
		int index=-1;
		
		source = new char[src.length()];
		System.arraycopy(src.toCharArray(), 0, source, 0, src.length());
		
		pattern = new char[target.length()];
		next = new int[target.length()];
		System.arraycopy(target.toCharArray(), 0, pattern, 0, target.length());
		
		//首先耗费pattern长度的时间计算部分匹配值
		next();
		
		int i=0,j=0;
		while(i<source.length&&j<pattern.length-1){
			if(source[i]==pattern[j]){
				i++;j++;
			}else{
				if(j==0){
					i++;
				}
				j=next[j];
			}
		}
		
		//表示匹配成功
		if(j==pattern.length-1){
			index = i-pattern.length+1;
		}
		
		return index;
	}
	/**
	 * 计算部分匹配表
	 */
	public void next(){
		
		next[0]=0;
		int i=1,j=0;
		
		while(i<pattern.length){
			if(pattern[i]==pattern[next[j]]){
				next[i]=next[j]+1;
				i++;
				j++;
			}else{
				if(j==0){
					//next[i]=0;
					i++;
					j=i-1;
					continue;
				}
				j = next[j];
			}
		}
	}
	/**
	 * 显示部分匹配值表
	 */
	public void showNext(){
		for(int element:next)
			System.out.print(element+" ");
	}
	public static void main(String[] args) {
		KMP kmp = new KMP();
		int k = kmp.match("abababdafdasabcfdfeaba", "bab");
		System.out.println(k);
		kmp.showNext();
	}
}
