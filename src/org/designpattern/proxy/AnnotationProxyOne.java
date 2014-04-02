package org.designpattern.proxy;

public class AnnotationProxyOne extends AnnotationProxy{

	@Override
	public void after() {
		// TODO Auto-generated method stub
		System.out.println("1a");
	}

	@Override
	public void before() {
		// TODO Auto-generated method stub
		System.out.println("1b");
	}

}
