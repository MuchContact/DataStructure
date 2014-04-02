package org.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ArrayListProxy {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyInvokeHandler hand = new MyInvokeHandler();
		hand.bind(new ArrayList<Integer>());
		List<Integer> list = (List<Integer>)Proxy.newProxyInstance(ArrayList.class.getClassLoader(), 
				ArrayList.class.getInterfaces(), hand);
		list.add(1);
		list.add(2);
		for(int i:list){
			System.out.println("data:"+i);
		}
	}
}
class MyInvokeHandler implements InvocationHandler{
	
	public void bind(Object o){
		this.o = o;
	}
	//public ArrayList<Integer> target = new ArrayList<Integer>();
	public Object o;
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(o==null){
			System.out.println("代理目标属性为空");
			return null;
		}
		long begin = System.currentTimeMillis();
		TimeUnit.MILLISECONDS.sleep(10);
		Object ob = method.invoke(o, args);
		long end = System.currentTimeMillis();
		System.out.println("time cost:"+(end-begin)+" ms");
		return ob;
	}
	
}
