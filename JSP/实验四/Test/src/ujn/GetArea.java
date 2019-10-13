package ujn;

public class GetArea {
	double a;
	double b;
	double c;
	double area;
	double area2;
	boolean triangle;
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
	public double getC() {
		return c;
	}
	public void setC(double c) {
		this.c = c;
	}
	public double getArea() {
		double p=(a+b+c)/2.0;
		if(triangle)
			area=Math.sqrt(p*(p-a)*(p-b)*(p-c));	
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getArea2() {
		area2=(a+b)*c/2.0;
		return area2;
	}
	public void setArea2(double area2) {
		this.area2 = area2;
	}
	public boolean isTriangle() {
		if(a<b+c&&b<a+c&&c<a+b)
			triangle=true;
		else
			triangle=false;
		return triangle;
	}
	public void setTriangle(boolean triangle) {
		this.triangle = triangle;
	}


}
