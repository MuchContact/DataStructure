package org.designpattern.proxy;

public class AnnotationProxyTwo extends AnnotationProxy{

	@Override
	public void after() {
		// TODO Auto-generated method stub
		System.out.println("2a");
	}

	@Override
	public void before() {
		// TODO Auto-generated method stub
		System.out.println("2b");
	}

}
