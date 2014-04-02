package org.designpattern.proxy;

public class AnnotationTest implements IAnnotationTest{

	@Override
	@Annotation(proxyClass=AnnotationProxyOne.class)
	public void showHeight() {
		// TODO Auto-generated method stub
		System.out.println("height");
	}

	@Override
	@Annotation(proxyClass=AnnotationProxyTwo.class)
	public void showWeight() {
		// TODO Auto-generated method stub
		System.out.println("weight");
	}
	public static void main(String[] args) {
		IAnnotationTest test = AnnotationLoader.getProxyBean(AnnotationTest.class);
		test.showHeight();
		test.showWeight();
	}
}
