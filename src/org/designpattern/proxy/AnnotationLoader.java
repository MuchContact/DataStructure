package org.designpattern.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationLoader {

	public static <T> T getProxyBean(Class<T> clazz){
            T t = (T) newinstance(clazz);
            Method[] methods = clazz.getMethods();
            
            for(int i=0;i<methods.length;i++){
            	Annotation pt = methods[i].getAnnotation(Annotation.class);
                    if(pt == null){
                            continue;
                    }
                    AnnotationProxy pb = (AnnotationProxy) newinstance(pt.proxyClass());

             //这里是重点，其中存在对象分级链，（在InvokeHandler中引用了一个t对象，切记）的情况存在
                    t = (T) pb.bind(t, methods[i].getName());
            }
            return t;
    }


	public static <E> E load(Class cls){
		E instance = (E)newinstance(cls);
		Method[] methods = cls.getMethods();
		for(Method m:methods){
			Annotation ann = m.getAnnotation(Annotation.class);
			if(ann==null){
				continue;
			}
			AnnotationProxy bean = (AnnotationProxy)newinstance(ann.proxyClass());
			instance = (E) bean.bind(instance, m.getName());
		}
		return instance;
	}
	//public static <T> T newinstance(final Class<T> cls){
	public static Object newinstance(final Class cls){
		try {
			Constructor cons = cls.getConstructor();
			return cons.newInstance(new Class[]{});
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
