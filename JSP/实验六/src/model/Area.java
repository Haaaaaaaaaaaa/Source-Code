package model;

public class Area {
double a;
double b;
double c;
double area1=-1;
double sd;
double xd;
double g;
double area2=-1;
String mess="可以构成三角形";

public String getMess() {
	return mess;
}

public void setMess(String mess1) {
	mess = mess1;
}

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

public double getArea1() {
	return area1;
}

public void setArea1(double s) {
	area1 = s;
}

public double getSd() {
	return sd;
}

public void setSd(double sd) {
	this.sd = sd;
}

public double getXd() {
	return xd;
}

public void setXd(double xd) {
	this.xd = xd;
}

public double getG() {
	return g;
}

public void setG(double g) {
	this.g = g;
}

public double getArea2() {
	return area2;
}

public void setArea2(double s2) {
	area2 = s2;
}
}
