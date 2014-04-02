package org.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProxyUtil {
	public enum MProxy{
		ArrayListProxy(new ArrayList<Object>()),IntegerProxy(2);
		private Object target;
		private MProxy(Object obj) {
			this.target = obj;
		}
		public Object getInstance(){
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
					target.getClass().getInterfaces(), new InvocationHandler() {
						
						@Override
						public Object invoke(Object proxy, Method method, Object[] args)
								throws Throwable {
							
							Object ooo = method.invoke(target, args);
							System.out.println("method "+method.getName()+" is invoked.");
							return ooo;
						}
					});
		}
	}
}
