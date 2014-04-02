package org.designpattern.proxy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.designpattern.proxy.ProxyUtil.MProxy;

public class ProxyUtilTest {
	public static void main(String[] args) {
		//ArrayList proxy = (ArrayList)MProxy.ArrayListProxy.getInstance();
		//proxy.compareTo(1);
		B b = new B();
		b.bind(b);
		b.invoke();
	}
	
}
abstract class A{
	private A a;
	public void bind(A aaa){
		this.a = aaa;
	}
	public void invoke(){
		show();
		
	}
	public abstract void show();
}
class B extends A{
	public void show(){
		System.out.println("B");
	}
}

