package org.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class AnnotationProxy implements InvocationHandler{
	
	public abstract void before();
	public abstract void after();
	
	private String methodName;
	private Object t;
	
	public Object bind(Object obj, String methodName){
		this.t = obj;
		this.methodName = methodName;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), 
				obj.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object o;
		if(method.getName().equals(methodName)){
			before();
			o = method.invoke(t, args);
			after();
		}else{
			o = method.invoke(t, args); 
		}
		return o;
	}
}
