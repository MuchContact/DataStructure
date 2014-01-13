package org.geo.sphere;

public class Sphere {
	private double rmin;
	private double e1;
	private double e2;

	public Sphere(double rmin, double e1, double e2) {
		super();
		this.rmin = rmin;
		this.e1 = e1;
		this.e2 = e2;
	}
	public double getRmin() {
		return rmin;
	}
	public void setRmin(double rmin) {
		this.rmin = rmin;
	}
	public double getE1() {
		return e1;
	}
	public void setE1(double e1) {
		this.e1 = e1;
	}
	public double getE2() {
		return e2;
	}
	public void setE2(double e2) {
		this.e2 = e2;
	}
	
}
