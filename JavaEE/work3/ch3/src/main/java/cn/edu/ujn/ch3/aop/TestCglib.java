package cn.edu.ujn.ch3.aop;

import net.sf.cglib.proxy.Enhancer;

public class TestCglib {
	
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		CglibProxy cglibProxy = new CglibProxy();
		
		enhancer.setSuperclass(userDaoImpl.getClass());
		enhancer.setCallback(cglibProxy);
		
		UserDaoImpl create = (UserDaoImpl) enhancer.create();
		create.addUser();
		create.deleteUser();		
	}
}
